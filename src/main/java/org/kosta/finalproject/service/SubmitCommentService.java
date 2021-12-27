package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;

import java.util.List;
import java.util.Map;

public interface SubmitCommentService {
    void deleteTaskComment(int submitNo);

    void registerSubmitComment(SubmitCommentDTO submitCommentDTO);

}
