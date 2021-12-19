package org.kosta.finalproject.controller;

import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.service.StudyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StudyCommentController {

    private final StudyCommentService studyCommentService;

    @Autowired
    public StudyCommentController(StudyCommentService studyCommentService) {
        this.studyCommentService = studyCommentService;
    }

    @GetMapping("/comment/{studyNo}")
    public String comment(@LoginUser SessionMember member, @PathVariable int studyNo, Model model) {
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }

        List<StudyCommentDTO> allStudyCommentList = studyCommentService.getAllStudyCommentList(studyNo);
        System.out.println("allStudyCommentList = " + allStudyCommentList);
        model.addAttribute("studyCommentList", allStudyCommentList);

        return "comment";
    }
}
