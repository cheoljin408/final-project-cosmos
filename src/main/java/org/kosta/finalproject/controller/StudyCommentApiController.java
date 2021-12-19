package org.kosta.finalproject.controller;

import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.service.StudyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Controller
public class StudyCommentApiController {

    private final StudyCommentService studyCommentService;

    @Autowired
    public StudyCommentApiController(StudyCommentService studyCommentService) {
        this.studyCommentService = studyCommentService;
    }

    @PostMapping("/api/studyComment/{studyNo}")
    public String registerStudyComment(@LoginUser SessionMember member, @PathVariable int studyNo, Model model, @RequestBody Map<String, Object> jsonData) {
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }

        studyCommentService.registerStudyComment(jsonData);

        List<StudyCommentDTO> allStudyCommentList = studyCommentService.getAllStudyCommentList(studyNo);
        System.out.println("allStudyCommentList = " + allStudyCommentList);
        model.addAttribute("studyCommentList", allStudyCommentList);

        return "fragments/study-comment :: fragment-study-comment";
    }
}
