package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
public class HomeController {

    // 지라 테스트 이슈

    private final StudyService studyService;
    private final HttpSession httpSession;

    @Autowired
    public HomeController(StudyService studyService, HttpSession httpSession) {
        this.studyService = studyService;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionMember member) {
        if(member != null) {
            httpSession.setAttribute("member", member);
            httpSession.setAttribute("picture", member.getPicture());
        }

        List<StudyMemberDTO> studyList = studyService.getStudyList3();
        log.debug("studyList: {}", studyList);
        model.addAttribute("studyList", studyList);
        return "index";
    }
}
