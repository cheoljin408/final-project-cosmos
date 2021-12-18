package org.kosta.finalproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.service.StudyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StudyCommentTest {

    @Autowired
    private StudyCommentService studyCommentService;

    @Test
    @DisplayName("스터디 상세 내부 댓글 조회")
    void getAllCommentListTest() {
        // given
        int[] studyCommentNoList = {3, 2, 1};

        // when
        List<StudyCommentDTO> allStudyCommentList = studyCommentService.getAllStudyCommentList(28);

        // then
        assertThat(allStudyCommentList.size()).isEqualTo(studyCommentNoList.length);
        for (StudyCommentDTO studyCommentDTO : allStudyCommentList) {
            int index = allStudyCommentList.indexOf(studyCommentDTO);
            assertThat(studyCommentDTO.getStudyCommentNo())
                    .isEqualTo(studyCommentNoList[index]);
        }
    }

}
