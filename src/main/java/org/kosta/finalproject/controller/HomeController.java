package org.kosta.finalproject.controller;

import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final StudyService studyService;

    @Autowired
    public HomeController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionMember member) {
        
        // 지라 연동 테스트 by syeon

        if(member != null) {
            model.addAttribute("member", member);
        }

        // List<Map<String, String>> studyList = studyService.getStudyList3();
        List<StudyMemberDTO> studyList = studyService.getStudyList3();
        for(int i=0; i<studyList.size(); i++) {
            System.out.println(studyList.get(i).toString());
            System.out.println(studyList.get(i).getMemberDTO().getName());
            System.out.println(studyList.get(i).getMemberDTO().getPicture());
        }
        System.out.println(studyList.size());
        model.addAttribute("studyList", studyList);
        return "index";
    }
}
