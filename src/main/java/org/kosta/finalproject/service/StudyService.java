package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyDTO;

import java.util.Map;

public interface StudyService {

    void registerStudy(StudyDTO studyDTO);

    void registerStudyMemberRole(String email);

    Map<String, Object> getStudyDetailByStudyNo(int studyNo);

    String findStudyMemberRoleByStudyNo(int studyNo, String email);

}
