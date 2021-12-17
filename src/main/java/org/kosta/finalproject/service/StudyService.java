package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.CategoryLangDTO;
import org.kosta.finalproject.model.domain.CategoryTypeDTO;
import org.kosta.finalproject.model.domain.StudyDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StudyService {

    void registerStudy(StudyDTO studyDTO);

    void registerStudyMemberRole(String email);

    List<CategoryTypeDTO> getCategoryTypeList();

    List<CategoryLangDTO> getCategoryLangList();

    Map<String, Object> getStudyDetailByStudyNo(int studyNo);

    List<StudyMemberDTO> getStudyList();
}
