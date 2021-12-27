package org.kosta.finalproject.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.service.SubmitCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class comment {
    @Autowired
    private SubmitCommentService submitCommentService;

    @Test
    @DisplayName("과제 제출 댓글 삭제하기")
    void 과제공지댓글삭제하기(){

    }
}
