package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;

public interface StudyService {

    List<StudyMemberDTO> getAllList();
    List<StudyMemberDTO> getStudyListByCategory();
    List<StudyMemberDTO> getStudyListByStudyNameAndDesc();
}
