package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;
import java.util.Map;

public interface StudyMemberService {
    List<Map<String, Object>> getStudyNameList(Map<String, Object> emailAndStudyNo);

    StudyMemberDTO getAllStudyInfo(int studyNo);
}
