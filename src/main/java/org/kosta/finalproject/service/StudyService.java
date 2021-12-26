package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;
import java.util.Map;

public interface StudyService {

    List<StudyMemberDTO> getStudyList3();

    List<StudyMemberDTO> getAllList();

    List<StudyMemberDTO> getStudyListByStudyNameAndDesc(String searchWord);

    List<StudyMemberDTO> getStudyListByCategory(String categoryVal);

    void registerStudy(Map<String,String> studyDTO);

    void registerStudyMemberRole(String email);

    Map<String, Object> getStudyDetailByStudyNo(int studyNo);

    String findStudyMemberRoleByStudyNo(int studyNo, String email);

    void modifyStudy(Map<String, String> studyDTO);

    void deleteStudyByStudyNo(int studyNo);

    List<StudyMemberDTO> getMystudyListByEmail(String email);

    void updateState(int studyNo,String studyState);
}
