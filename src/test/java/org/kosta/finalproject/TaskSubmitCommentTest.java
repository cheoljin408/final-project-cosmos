package org.kosta.finalproject;

import org.junit.jupiter.api.Test;
import org.kosta.finalproject.service.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class TaskSubmitCommentTest {

    private final TaskCommentService taskCommentService;

    @Autowired
    public TaskSubmitCommentTest(TaskCommentService taskCommentService) {
        this.taskCommentService = taskCommentService;
    }

    @Test
    void registerTaskCommentTest() {

    }
}
