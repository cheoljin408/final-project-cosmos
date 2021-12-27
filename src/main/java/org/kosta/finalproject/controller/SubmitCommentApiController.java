package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.service.SubmitCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class SubmitCommentApiController {

    private final SubmitCommentService submitCommentService;

    @Autowired
    public SubmitCommentApiController(SubmitCommentService submitCommentService) {
        this.submitCommentService = submitCommentService;
    }
    
    // 과제 제출 등록
    @PostMapping("/api/registerTaskComment/{studyNo}/{taskNo}")
    public String registerTaskComment() {
        return "asdf";
    }
    
    // 과제 제출 수정
    
    // 과제 제출 삭제
}
