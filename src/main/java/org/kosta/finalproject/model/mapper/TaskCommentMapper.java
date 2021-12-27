package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;

import java.util.List;

@Mapper
public interface TaskCommentMapper {
    List<SubmitCommentDTO> findSubmitCommentById(int studyNo, int taskNo);
}
