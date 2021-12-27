package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.SubmitCommentDTO;

import java.util.HashMap;
import java.util.List;

public interface SubmitCommentService {
    public List<HashMap<String, String>> getAllSubmitComment(int studyNo, int taskNo);
}
