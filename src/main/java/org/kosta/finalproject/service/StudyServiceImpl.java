package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudyServiceImpl implements StudyService{

    private final StudyMapper studyMapper;

    @Autowired
    public StudyServiceImpl(StudyMapper studyMapper) {
        this.studyMapper = studyMapper;
    }

    public List<StudyMemberDTO> getStudyList3() {
        List<StudyMemberDTO> studyList = studyMapper.getStudyList3();
        return studyList;
    }

    @Override
    public List<StudyMemberDTO> getAllList() {
        return studyMapper.getAllStudyList();
    }

    @Override
    public List<Map<String, Object>> getStudyListByCategory(String categoryVal) {
        return studyMapper.getStudyListByCategory(categoryVal);
    }

    @Override
    public List<Map<String, Object>> getStudyListByStudyNameAndDesc(String searchWord) {
        return studyMapper.getStudyListByStudyNameAndDesc(searchWord);
    }
}
