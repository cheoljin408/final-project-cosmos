package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.service.NoticeService;
import org.kosta.finalproject.service.StudyMemberService;
import org.kosta.finalproject.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/task")
public class TaskController0 {

    private final NoticeService noticeService;
    private final StudyMemberService studyMemberService;

    @Autowired
    public TaskController0(NoticeService noticeService, StudyMemberService studyMemberService) {
        this.noticeService = noticeService;
        this.studyMemberService = studyMemberService;
    }

    @GetMapping("/list/{studyNo}")
    public String noticeList(@PathVariable int studyNo, Model model,
                             HttpServletResponse response, @LoginUser SessionMember member) {
        model.addAttribute("noticeList", noticeService.getAllNoticeList(studyNo));


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

        return "lms/task/list";
    }
}
