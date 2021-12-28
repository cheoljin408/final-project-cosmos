package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.SubmitCommentDTO;

import java.util.HashMap;
import java.util.List;

public interface SubmitCommentService {
  
    List<HashMap<String, String>> getAllSubmitComment(int studyNo, int taskNo);

    void deleteTaskComment(int submitNo);

    void registerSubmitComment(SubmitCommentDTO submitCommentDTO);

    void updateSubmitComment(SubmitCommentDTO submitCommentDTO);
}
