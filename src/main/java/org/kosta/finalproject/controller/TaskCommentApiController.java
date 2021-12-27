package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.service.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class TaskCommentApiController {

    private final TaskCommentService taskCommentService;

    @Autowired
    public TaskCommentApiController(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }
    
    // 과제 제출 등록
    @PostMapping("/api/registerTaskComment/{studyNo}/{taskNo}")
    public String registerTaskComment() {
        return "asdf";
    }
    
    // 과제 제출 수정
    
    // 과제 제출 삭제
}
