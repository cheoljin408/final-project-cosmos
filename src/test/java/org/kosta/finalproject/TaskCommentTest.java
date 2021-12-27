package org.kosta.finalproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;
import org.kosta.finalproject.service.TaskCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TaskCommentTest {

    Logger logger = (Logger) LoggerFactory.getLogger(TaskTest.class);

    @Autowired
    private TaskCommentService taskCommentService;

    @Test
    @DisplayName("과제 제출 댓글 조회 테스트")
    void 과제제출댓글조회() {
        //given
        int studyNo = 108;
        int taskNo = 1;
        // 108번 스터디의 과제 공지 1번 글에 과제 제출 댓글 데이터 총 3개를 insert 한 상태

        //when
        List<SubmitCommentDTO> scl = taskCommentService.findSubmitCommentById(studyNo, taskNo);

        //then
        assertThat(scl.size()).isEqualTo(3);
        for (SubmitCommentDTO submitCommentDTO : scl) {
            logger.info("submitCommentDTO: {}", submitCommentDTO);
        }
    }
}
