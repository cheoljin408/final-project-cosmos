package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.domain.TaskFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.service.FileStoreService;
import org.kosta.finalproject.service.StudyMemberService;
import org.kosta.finalproject.service.StudyService;
import org.kosta.finalproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final FileStoreService fileStoreService;
    private final StudyMemberService studyMemberService;
    private final StudyService studyService;

    @Autowired
    public TaskController(TaskService taskService, FileStoreService fileStoreService, StudyMemberService studyMemberService, StudyService studyService) {
        this.taskService = taskService;
        this.fileStoreService = fileStoreService;
        this.studyMemberService = studyMemberService;
        this.studyService = studyService;
    }

    //LMS 사이드바 적용 과제 공지사항 등록페이지
    @GetMapping("/register/{studyNo}")
    public String lmsRegisterTask(@PathVariable int studyNo, Model model, @LoginUser SessionMember member) {

        if (!studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail()).equals("스터디리더")) {
            model.addAttribute("studyNo", studyNo);
            return "lms/lms-error";
        }

        model.addAttribute("studyNo", studyNo);

        // 내가 속한 스터디 이름 리스트 가져오기
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", member.getEmail());
        emailAndStudyNo.put("studyNo", studyNo);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        // log.info("studyNameList: {}", studyNameList);
        model.addAttribute("studyNameList", studyNameList);

        // 해당 스터디에대한 전체 정보 가져오기
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        // log.info("allStudyInfo: {}", allStudyInfo);
        model.addAttribute("allStudyInfo", allStudyInfo);

        return "lms/task/register";
    }

    //과제 공지 등록
    @Transactional
    @PostMapping("/register/{studyNo}")
    public String registerNotice(@PathVariable int studyNo, @LoginUser SessionMember member, @ModelAttribute TaskFormDTO taskFormDTO, RedirectAttributes redirectAttributes) throws IOException {

        // log.info("taskFormDTO: {}", taskFormDTO);
        // 파일에 저장
        // MultipartFile attachFile = form.getAttachFile();
        // UploadFile attachFile = fileStore.storeFile(attachFile);

        // log.info("attach:{}", taskFormDTO.getAttachFiles());
        List<UploadFile> attachFiles = fileStoreService.storeFiles(taskFormDTO.getAttachFiles());
        // log.info("taskController, registerTask, attachFiles: {}", attachFiles);
        // List<MultipartFile> imageFiles = form.getImageFiles();
        // List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
        List<UploadFile> storeImageFiles = fileStoreService.storeFiles(taskFormDTO.getImageFiles());
        // log.info("taskController, registerTask, storeImageFiles: {}", storeImageFiles);

        //데이터베이스에 저장
        int taskNo = taskService.registerTask(studyNo, member.getEmail(), taskFormDTO, attachFiles, storeImageFiles);
        // noticeNo = noticeService.registerNotice(studyNo, member.getEmail(), noticeFormDTO, attachFiles, storeImageFiles);
        // log.info("taskNo: {}", taskNo);

        redirectAttributes.addAttribute("taskNo", taskNo);
        redirectAttributes.addAttribute("studyNo", studyNo);

        return "redirect:/task/detail/{studyNo}/{taskNo}";
    }

    @GetMapping("/list/{studyNo}")
    public String noticeList(@PathVariable int studyNo, Model model, @LoginUser SessionMember member) {
        model.addAttribute("taskList", taskService.getAllTaskListByStudyNo(studyNo));


        // 내가 속한 스터디 이름 리스트 가져오기
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", member.getEmail());
        emailAndStudyNo.put("studyNo", studyNo);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        // log.info("studyNameList: {}", studyNameList);
        model.addAttribute("studyNameList", studyNameList);

        // 해당 스터디에대한 전체 정보 가져오기
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        // log.info("allStudyInfo: {}", allStudyInfo);
        model.addAttribute("allStudyInfo", allStudyInfo);

        return "lms/task/list";
    }

    @RequestMapping("/detail/{studyNo}/{taskNo}")
    public String taskDetail(Model model,
                             @LoginUser SessionMember member,
                             @PathVariable int taskNo,
                             @PathVariable int studyNo) {

        // 내가 속한 스터디 이름 리스트 가져오기
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", member.getEmail());
        emailAndStudyNo.put("studyNo", studyNo);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        model.addAttribute("studyNameList", studyNameList);

        // 해당 스터디에대한 전체 정보 가져오기
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        model.addAttribute("allStudyInfo", allStudyInfo);
        /* ******************  중복 코드 ****************** */

        // 1. 과제 정보 가져오기
        Map<String, Object> result = taskService.getTaskDetailByTaskNo(taskNo);

        // 2. 업로드된 이미지 및 파일 정보 가져오기
        List<UploadFile> files = taskService.findFilesById(taskNo);
        List<UploadFile> images = taskService.findImagesById(taskNo);

        // 3. 모델에 입력
        model.addAttribute("images", images);
        model.addAttribute("files", files);
        model.addAttribute("taskInfo", result);

        return "lms/task/detail";
    }

    /**
     *   과제공지 업데이트 폼으로 이동
     */
    @PostMapping("/update/form/{studyNo}")
    public String updateTaskForm(@RequestParam int taskNo, @PathVariable int studyNo ,
                                   @LoginUser SessionMember member, Model model) {
        model.addAttribute("taskInfo", taskService.getTaskDetailByTaskNo(taskNo));
        log.info("taskInfo: {}", taskService.getTaskDetailByTaskNo(taskNo));
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

        return "lms/task/update";
    }

    /**
     * 과제공지 수정
     */
    @PostMapping("/update/{studyNo}")
    @Transactional
    public String update(@PathVariable int studyNo, @ModelAttribute TaskFormDTO taskFormDTO,
                         Model model, @LoginUser SessionMember member, @RequestParam int taskNo,
                         RedirectAttributes redirectAttributes) throws IOException {
        // 1. Content와 Title 정보를 업데이트
        log.info("content, title uploading");
        taskService.updateTaskByTaskNo(taskFormDTO.getTaskTitle(),
                taskFormDTO.getTaskContent(),
                taskNo);
        log.info("delete original images, files");
        // 2. 기존의 이미지와 파일들을 삭제
        taskService.deleteTaskFileByTaskNo(taskNo);

        // 3. 이미지와 파일들을 재업로드
        List<UploadFile> storeImageFiles = fileStoreService.storeFiles(taskFormDTO.getImageFiles());
        List<UploadFile> attachFiles = fileStoreService.storeFiles(taskFormDTO.getAttachFiles());

        // 3.2 ) -> 실질적으로 저장
        taskService.registerTaskFiles(taskNo, taskFormDTO, attachFiles, storeImageFiles);

        redirectAttributes.addAttribute("taskNo", taskNo);
        redirectAttributes.addAttribute("studyNo", studyNo);

        return "redirect:/task/detail/{studyNo}/{taskNo}";
    }
    /**
     * 과제 공지사항 삭제
     */
    @PostMapping("/delete")
    @ResponseBody
    public String deleteTask(@RequestParam int taskNo) {
        taskService.deleteTask(taskNo);
        return null;
    }

    // 이미지 다운로드, 반환. 보안에 취약해서 보안 로직을 추가해야 함
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        // "file:C:/Users/Yong Lee/test-upload/UUID.확장자"
        // UrlResource: 파일에 직접 접근해서 리소스를 가져오고 스트림이 되어서 반환된다
        return new UrlResource("file:" + fileStoreService.getFullPath(filename));
    }

    // 헤더에 추가해야 할 사항이 있어서 ResponseBody 는 사용하지 않았음
    // 첨부파일 다운로드
    @GetMapping("/attach/{taskNo}/{index}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable int taskNo,
                                                   @PathVariable int index) throws MalformedURLException {
        List<UploadFile> files = taskService.findFilesById(taskNo);

        String uploadFileName = files.get(index).getUploadFileName();
        String storeFileName = files.get(index).getStoreFileName();

        UrlResource resource = new UrlResource("file:" + fileStoreService.getFullPath(storeFileName));

        // log.info("uploadFileName={}", uploadFileName);
        // log.info("storeFileName={}", storeFileName);

        // 한글 깨짐 방지를 위한 인코딩
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);

        // 파일 다운로드를 위한 규약. 사용하지 않을 경우 브라우저에서 다운이 아닌 읽기가 동작
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
