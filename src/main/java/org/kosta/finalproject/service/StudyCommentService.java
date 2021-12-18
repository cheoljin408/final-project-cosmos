package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyCommentDTO;

import java.util.List;

public interface StudyCommentService {
    List<StudyCommentDTO> getAllStudyCommentList(int studyNo);
}
