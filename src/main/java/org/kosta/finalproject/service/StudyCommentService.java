package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyCommentDTO;

import java.util.List;
import java.util.Map;

public interface StudyCommentService {
    List<StudyCommentDTO> getAllStudyCommentList(int studyNo);

    void registerStudyComment(Map<String, Object> jsonData);

    void updateStudyComment(Map<String, Object> jsonData);

    Map<String, Object> getStudyCommentByStudyCommentNo(int studyCommentNo);
}
