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
    public testLmsController(StudyMemberService studyMemberService, NoticeService noticeService, TaskService taskService,StudyService studyService) {
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
