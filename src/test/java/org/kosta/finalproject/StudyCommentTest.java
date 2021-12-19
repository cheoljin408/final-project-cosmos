package org.kosta.finalproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.service.StudyCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    @DisplayName("댓글 등록 테스트")
    void registerStudyCommentTest() {
        // given
        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("studyCommentContent", "testComment");
        jsonData.put("email", "test@test.com");
        jsonData.put("studyNo", "28");

        // when
        studyCommentService.registerStudyComment(jsonData);
        List<StudyCommentDTO> allStudyCommentList = studyCommentService.getAllStudyCommentList(28);

        // then
        assertThat(jsonData.get("studyCommentContent")).isEqualTo(allStudyCommentList.get(0).getStudyCommentContent());
    }

    @Test
    @DisplayName("댓글 수정 테스트")
    void updateStudyComment() {

        // given
        Map<String, Object> jsonData = new HashMap<String, Object>();
        jsonData.put("studyCommentContent", "updateComment");
        jsonData.put("studyCommentNo", "43");

        // when
        studyCommentService.updateStudyComment(jsonData);
        Map<String, Object> studyComment = studyCommentService.getStudyCommentByStudyCommentNo(43);

        // then
        assertThat(jsonData.get("studyCommentContent")).isEqualTo(studyComment.get("STUDY_COMMENT_CONTENT"));

    }

}
