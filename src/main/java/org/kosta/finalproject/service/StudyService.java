package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.CategoryLangDTO;
import org.kosta.finalproject.model.domain.CategoryTypeDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;
import java.util.Map;

public interface StudyService {

    List<StudyMemberDTO> getAllList();

    List<Map<String, Object>> getStudyListByStudyNameAndDesc(String searchWord);

    List<Map<String, Object>> getStudyListByCategory(String categoryVal);

}
