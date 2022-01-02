package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.service.StudyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class StudyCommentApiController {

    private final StudyCommentService studyCommentService;

    @Autowired
    public StudyCommentApiController(StudyCommentService studyCommentService) {
        this.studyCommentService = studyCommentService;
    }

    @PostMapping("/api/registerStudyComment/{studyNo}")
    public String registerStudyComment(@PathVariable int studyNo, Model model, @RequestBody Map<String, Object> jsonData) {
        studyCommentService.registerStudyComment(jsonData);

        List<StudyCommentDTO> allStudyCommentList = studyCommentService.getAllStudyCommentList(studyNo);
        System.out.println("allStudyCommentList = " + allStudyCommentList);
        model.addAttribute("studyCommentList", allStudyCommentList);

        return "fragments/study-comment :: fragment-study-comment";
    }

    @PutMapping("/api/updateStudyComment/{studyNo}")
    public String updateStudyComment(@RequestBody Map<String, Object> jsonData, @PathVariable int studyNo, Model model) {
        studyCommentService.updateStudyComment(jsonData);

        List<StudyCommentDTO> allStudyCommentList = studyCommentService.getAllStudyCommentList(studyNo);
        System.out.println("allStudyCommentList = " + allStudyCommentList);
        model.addAttribute("studyCommentList", allStudyCommentList);

        return "fragments/study-comment :: fragment-study-comment";
    }

    @DeleteMapping("/api/deleteStudyComment/{studyNo}")
    public String deleteStudyComment(@RequestBody Map<String, Object> jsonData, @PathVariable int studyNo, Model model) {
        System.out.println("jsonData = " + jsonData);
        studyCommentService.deleteStudyComment(jsonData);

        List<StudyCommentDTO> allStudyCommentList = studyCommentService.getAllStudyCommentList(studyNo);
        System.out.println("allStudyCommentList = " + allStudyCommentList);
        model.addAttribute("studyCommentList", allStudyCommentList);

        return "fragments/study-comment :: fragment-study-comment";
    }
}
