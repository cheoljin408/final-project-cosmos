package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.CategoryLangDTO;
import org.kosta.finalproject.model.domain.CategoryTypeDTO;
import org.kosta.finalproject.model.domain.StudyDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudyServiceImpl implements StudyService{

    private StudyMapper studyMapper;

    @Autowired
    public StudyServiceImpl(StudyMapper studyMapper) {
        this.studyMapper = studyMapper;
    }

    @Override
    public void registerStudy(StudyDTO studyDTO) {
        studyMapper.registerStudy(studyDTO);
    }

    @Override
    public List<CategoryTypeDTO> getCategoryTypeList() {
        return studyMapper.getCategoryTypeList();
    }

    @Override
    public List<CategoryLangDTO> getCategoryLangList() {
        return studyMapper.getCategoryLangList();
    }

    @Override
    public void registerStudyMemberRole(String email) {
        studyMapper.registerStudyMemberRole(email);
    }

    @Override
    public Map<String, Object> getStudyDetailByStudyNo(int studyNo) {
        return studyMapper.getStudyDetailByStudyNo(studyNo);
    }

    @Override
    public List<StudyMemberDTO> getStudyList() {
        return studyMapper.getStudyList();
    }

}
