package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyServiceImpl implements StudyService{

    private StudyMapper studyMapper;

    @Autowired
    public StudyServiceImpl(StudyMapper studyMapper) {
        this.studyMapper = studyMapper;
    }

    @Override
    public List<StudyMemberDTO> getStudyList3() {
        List<StudyMemberDTO> studyList = studyMapper.getStudyList3();
        return studyList;
    }
}
