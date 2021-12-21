package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.service.NoticeService;
import org.kosta.finalproject.service.StudyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class testLmsController {

    private final StudyMemberService studyMemberService;
    private final NoticeService noticeService;

    @Autowired
    public testLmsController(StudyMemberService studyMemberService, NoticeService noticeService) {
        this.studyMemberService = studyMemberService;
        this.noticeService = noticeService;
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

        // 해당 스터디의 공지사항 리스트 가져오기
        List<NoticeDTO> recentNoticeList = noticeService.getRecentNoticeList(studyNo);
        log.info("recentNoticeList: {}", recentNoticeList);
        model.addAttribute("recentNoticeList", recentNoticeList);

        return "lms/lms-main";
    }
}
