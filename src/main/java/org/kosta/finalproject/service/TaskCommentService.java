package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;

import java.util.List;
import java.util.Map;

public interface TaskCommentService {
    void deleteTaskComment(int submitNo);

    List<SubmitCommentDTO> getAllTaskCommentList(int taskNo);
}
