package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.StudyCommentDTO;

import java.util.List;

@Mapper
public interface TaskCommentMapper {

    void deleteTaskComment(int submitNo);

    List<StudyCommentDTO> getAllTaskCommentList(int taskNo);

}
