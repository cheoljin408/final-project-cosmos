package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.domain.TaskFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/task")
public class JHtaskController {

    private final TaskService taskService;
    private final FileStoreService fileStoreService;
    private final StudyMemberService studyMemberService;
    private final StudyService studyService;

    @Autowired
    public JHtaskController(TaskService taskService, FileStoreService fileStoreService, StudyMemberService studyMemberService,StudyService studyService) {
        this.taskService = taskService;
        this.fileStoreService = fileStoreService;
        this.studyMemberService = studyMemberService;
        this.studyService = studyService;
    }

    //LMS 사이드바 적용 과제 공지사항 등록페이지
    @GetMapping("/register/{studyNo}")
    public String lmsRegisterTask(@PathVariable int studyNo, Model model, @LoginUser SessionMember member) {

        if(!studyService.findStudyMemberRoleByStudyNo(studyNo,member.getEmail()).equals("스터디리더")){
            model.addAttribute("studyNo",studyNo);
            return "lms/lms-error";
        }

        model.addAttribute("studyNo", studyNo);

        // 내가 속한 스터디 이름 리스트 가져오기
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", member.getEmail());
        emailAndStudyNo.put("studyNo", studyNo);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        log.info("studyNameList: {}", studyNameList);
        model.addAttribute("studyNameList", studyNameList);

        // 해당 스터디에대한 전체 정보 가져오기
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        log.info("allStudyInfo: {}", allStudyInfo);
        model.addAttribute("allStudyInfo", allStudyInfo);

        return "lms/task/register";
    }

    //과제 공지 등록
    @Transactional
    @PostMapping("/register/{studyNo}")
    public String registerNotice(@PathVariable int studyNo, @LoginUser SessionMember member, @ModelAttribute TaskFormDTO taskFormDTO, RedirectAttributes redirectAttributes, Model model)  throws IOException {

        log.info("taskFormDTO: {}", taskFormDTO);
        // 파일에 저장
        // MultipartFile attachFile = form.getAttachFile();
        // UploadFile attachFile = fileStore.storeFile(attachFile);

        log.info("attach:{}", taskFormDTO.getAttachFiles());
        List<UploadFile> attachFiles = fileStoreService.storeFiles(taskFormDTO.getAttachFiles());
        log.info("taskController, registerTask, attachFiles: {}", attachFiles);
        // List<MultipartFile> imageFiles = form.getImageFiles();
        // List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
        List<UploadFile> storeImageFiles = fileStoreService.storeFiles(taskFormDTO.getImageFiles());
        log.info("taskController, registerTask, storeImageFiles: {}", storeImageFiles);

        //데이터베이스에 저장
        int taskNo = taskService.registerTask(studyNo, member.getEmail(), taskFormDTO, attachFiles, storeImageFiles);
        // noticeNo = noticeService.registerNotice(studyNo, member.getEmail(), noticeFormDTO, attachFiles, storeImageFiles);
        log.info("taskNo: {}", taskNo);

        redirectAttributes.addAttribute("taskNo", taskNo);
        redirectAttributes.addAttribute("studyNo", studyNo);

        return "redirect:/task/detail/{studyNo}/{taskNo}";
    }
}
