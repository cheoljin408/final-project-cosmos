package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyMapper {

    List<StudyMemberDTO> getStudyList3();

    List<StudyMemberDTO> getAllStudyList();

    List<Map<String, Object>> getStudyListByStudyNameAndDesc(String searchWord);

    List<Map<String, Object>> getStudyListByCategory(String categoryVal);
  
}
