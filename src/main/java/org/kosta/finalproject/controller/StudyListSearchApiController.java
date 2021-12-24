package org.kosta.finalproject.controller;

import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudyListSearchApiController {

    private StudyService studyService;
    @Autowired
    public StudyListSearchApiController(StudyService studyService) {
        this.studyService = studyService;
    }

    // mapping : /api/studyListSearch/{value}

    @GetMapping("/api/studyListSearchByCategory")
    public String getStudyListByCategory(@LoginUser SessionMember member, Model model, @RequestParam String categoryVal){
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        System.out.println(categoryVal);

        List<StudyMemberDTO> list = studyService.getStudyListByCategory(categoryVal);
        model.addAttribute("studyList",list);
        return "fragments/getallstudylist :: fragment-getallstudylist";
    }

    @GetMapping("/api/studyListSearchByStudyNameAndDesc")
    public String getStudyListByStudyNameAndDesc(@LoginUser SessionMember member, Model model, @RequestParam String searchWord){
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        List<StudyMemberDTO> list = studyService.getStudyListByStudyNameAndDesc(searchWord);
        model.addAttribute("studyList",list);
        return "fragments/getallstudylist :: fragment-getallstudylist";
    }
}
