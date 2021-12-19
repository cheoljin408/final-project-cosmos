package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.StudyCommentDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyCommentMapper {
    List<StudyCommentDTO> getAllStudyCommentList(int studyNo);

    void registerStudyComment(Map<String, Object> jsonDate);

    void updateStudyComment(Map<String, Object> jsonData);

    Map<String, Object> getStudyCommentByStudyCommentNo(int studyCommentNo);
}
