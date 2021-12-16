package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;

@Mapper
public interface StudyMapper {
    List<StudyMemberDTO> getAllStudyList();
    List<StudyMemberDTO> getStudyListByCategory();
    List<StudyMemberDTO> getStudyListByStudyNameAndDesc();
}
