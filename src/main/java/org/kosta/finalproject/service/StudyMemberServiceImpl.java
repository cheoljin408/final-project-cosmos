package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.mapper.StudyMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudyMemberServiceImpl implements StudyMemberService {

    private final StudyMemberMapper studyMemberMapper;

    @Autowired
    public StudyMemberServiceImpl(StudyMemberMapper studyMemberMapper) {
        this.studyMemberMapper = studyMemberMapper;
    }

    @Override
    public List<Map<String, Object>> getStudyNameList(Map<String, Object> emailAndStudyNo) {
        List<Map<String, Object>> studyNameList = studyMemberMapper.getStudyNameList(emailAndStudyNo);
        return studyNameList;
    }

    @Override
    public StudyMemberDTO getAllStudyInfo(int studyNo) {
        StudyMemberDTO allStudyInfo = studyMemberMapper.getAllStudyInfo(studyNo);
        return allStudyInfo;
    }
}
