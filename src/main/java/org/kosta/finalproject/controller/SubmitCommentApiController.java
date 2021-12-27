package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;
import org.kosta.finalproject.service.SubmitCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Controller
public class SubmitCommentApiController {

    private final SubmitCommentService submitCommentService;

    @Autowired
    public SubmitCommentApiController(SubmitCommentService submitCommentService) {
        this.submitCommentService = submitCommentService;
    }
    
    // 과제 제출 등록
    
    // 과제 제출 수정
    
    // 과제 제출 삭제
    @DeleteMapping("/api/deleteTaskComment/{submitNo}")
    public String deleteTaskComment(@RequestBody int submitNo, @PathVariable int taskNo, Model model) {
        System.out.println("submit = " + submitNo);
        submitCommentService.deleteTaskComment(submitNo);

        List<SubmitCommentDTO> allTaskCommentList = submitCommentService.getAllTaskCommentList(taskNo);
        model.addAttribute("studyCommentList", allTaskCommentList);

        return "fragments/task-submit-comment :: fragment-task-submit-comment";
    }
}
