package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.service.NoticeService;
import org.kosta.finalproject.service.StudyMemberService;
import org.kosta.finalproject.service.StudyService;
import org.kosta.finalproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class testLmsController {

    private final StudyMemberService studyMemberService;
    private final NoticeService noticeService;
    private final TaskService taskService;
    private final StudyService studyService;

    @Autowired
    public testLmsController(StudyMemberService studyMemberService, NoticeService noticeService, TaskService taskService, StudyService studyService) {
        this.studyMemberService = studyMemberService;
        this.noticeService = noticeService;
        this.taskService = taskService;
        this.studyService = studyService;
    }

    @GetMapping("/lms/{studyNo}")
    public String lms(@LoginUser SessionMember member, @PathVariable int studyNo, Model model) {
        // add studyNo
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

        // 해당 스터디의 최근 공지사항 리스트 가져오기
        List<NoticeDTO> recentNoticeList = noticeService.getRecentNoticeList(studyNo);
        log.info("recentNoticeList: {}", recentNoticeList);
        model.addAttribute("recentNoticeList", recentNoticeList);

        // 해당 스터디의 최근 과제 게시판 리스트 가져오기
        List<TaskDTO> recentTaskList = taskService.getRecentTaskList(studyNo);
        log.info("recentTaskList: {}", recentTaskList);
        model.addAttribute("recentTaskList", recentTaskList);

        return "lms/lms-main";
    }

    /**
     * 스터디 정보 수정
     * @return: 스터디 수정 폼 페이지
     */
    @GetMapping("/lms/updateStudy/{studyNo}")
    public String updateStudy(@PathVariable int studyNo, Model model, @LoginUser SessionMember member) {
        if (!studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail()).equals("스터디리더")) {
            model.addAttribute("studyNo", studyNo);
            return "lms/lms-error";
        }
        model.addAttribute("study", studyService.getStudyDetailByStudyNo(studyNo));
        log.info("modify study name: {}", studyService.getStudyDetailByStudyNo(studyNo).get("STUDY_NAME"));
        model.addAttribute(studyNo);
        return "study/modify";
    }

    @ResponseBody
    @PostMapping("/lms/updateStudy/{studyNo}")
    public int updateStudy(@PathVariable int studyNo, Model model, @RequestBody Map<String, String> jsonData) {

        jsonData.put("studyNo", String.valueOf(studyNo));
        log.info("jsonData:{}", jsonData);
        studyService.modifyStudy(jsonData);
        model.addAttribute("studyNo", studyNo);
        return 0;
    }

    @PostMapping("/lms/deleteStudy")
    public String deleteStudy(@RequestParam int studyNo, @LoginUser SessionMember member) {
        if (!studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail()).equals("스터디리더")) {
            return "lms/lms-error";
        }

        studyService.deleteStudyByStudyNo(studyNo);

        return "redirect:/";
    }

    //스터디 상태 변경
    @PostMapping("/updateState")
    public String updateState(@RequestParam int studyNo, @RequestParam String studyState, @LoginUser SessionMember member){
        log.info("findStudyMemberRoleByStudyNo : {}",studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail()));
        if(studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail()).equals("스터디리더")){
            studyService.updateState(studyNo,studyState);
        }
        return "redirect:/lms/"+studyNo;
    }

}
