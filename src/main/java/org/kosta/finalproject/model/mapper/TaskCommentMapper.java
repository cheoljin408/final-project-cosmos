package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;

import java.util.List;

@Mapper
public interface TaskCommentMapper {

    void deleteTaskComment(int submitNo);

    List<SubmitCommentDTO> getAllTaskCommentList(int taskNo);

}
