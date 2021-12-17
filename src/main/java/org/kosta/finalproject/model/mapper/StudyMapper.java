package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.CategoryLangDTO;
import org.kosta.finalproject.model.domain.CategoryTypeDTO;
import org.kosta.finalproject.model.domain.StudyDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyMapper {

    void registerStudy(StudyDTO studyDTO);

    void registerStudyMemberRole(String email);

    Map<String, Object> getStudyDetailByStudyNo(int studyNO);

    List<CategoryTypeDTO> getCategoryTypeList();

    List<CategoryLangDTO> getCategoryLangList();

    List<StudyMemberDTO> getStudyList();
}
