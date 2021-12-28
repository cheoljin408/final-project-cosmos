package org.kosta.finalproject.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.service.SubmitCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class comment {
    @Autowired
    private SubmitCommentService submitCommentService;

    @Test
    @DisplayName("과제 제출 댓글 삭제하기")
    @Rollback   // delete 후 Rollback
    void 과제공지댓글삭제하기(){

        // given : 테스트 환경에서 local DB에 데이터 4개가 존재
        // assertThat(submitCommentService.getAllTaskCommentList(2).size()).isEqualTo(4);

        // when  : 4개에서 1개를 삭제
        // submitCommentService.deleteTaskComment(4);
        
        // then : 개수 : 3
        // assertThat(submitCommentService.getAllTaskCommentList(2).size()).isEqualTo(3);
    }
}
