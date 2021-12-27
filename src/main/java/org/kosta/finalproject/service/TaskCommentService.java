package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.SubmitCommentDTO;

import java.util.List;

public interface TaskCommentService {
    public List<SubmitCommentDTO> findSubmitCommentById(int studyNo, int taskNo);
}
