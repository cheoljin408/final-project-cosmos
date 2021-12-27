package org.kosta.finalproject;

import org.junit.jupiter.api.Test;
import org.kosta.finalproject.service.SubmitCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class SubmitCommentTest {

    private final SubmitCommentService submitCommentService;

    @Autowired
    public SubmitCommentTest(SubmitCommentService submitCommentService) {
        this.submitCommentService = submitCommentService;
    }

    @Test
    void registerTaskCommentTest() {

    }
}
