package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyCommentDTO;

import java.util.List;
import java.util.Map;

public interface TaskCommentService {
    void deleteTaskComment(int submitNo);

    List<StudyCommentDTO> getAllTaskCommentList(int taskNo);
}
