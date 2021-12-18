package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;
import java.util.Map;

public interface StudyService {

    List<StudyMemberDTO> getStudyList3();

    List<StudyMemberDTO> getAllList();

    List<Map<String, Object>> getStudyListByStudyNameAndDesc(String searchWord);

    List<Map<String, Object>> getStudyListByCategory(String categoryVal);

    void registerStudy(Map<String,String> studyDTO);

    void registerStudyMemberRole(String email);

    Map<String, Object> getStudyDetailByStudyNo(int studyNo);

    String findStudyMemberRoleByStudyNo(int studyNo, String email);

    void modifyStudy(Map<String, String> studyDTO);
}
