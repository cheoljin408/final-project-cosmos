package org.kosta.finalproject.service;

import org.kosta.finalproject.model.mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyServiceImpl implements StudyService{

    private final StudyMapper studyMapper;

    @Autowired
    public StudyServiceImpl(StudyMapper studyMapper) {
        this.studyMapper = studyMapper;
    }
}
