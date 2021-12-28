package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;

import java.util.HashMap;
import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;

import java.util.List;

@Mapper
public interface SubmitCommentMapper {
  
    List<HashMap<String, String>> getAllSubmitComment(int studyNo, int taskNo);

    void deleteTaskComment(int submitNo);
  
    void registerSubmitComment(SubmitCommentDTO submitCommentDTO);

    void updateSubmitComment(SubmitCommentDTO submitCommentDTO);
}
