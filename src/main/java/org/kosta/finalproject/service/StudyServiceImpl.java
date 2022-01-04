package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public void registerStudy(Map<String, String> studyDTO) {
        studyMapper.registerStudy(studyDTO);
        System.out.println("studyDTO = " + studyDTO);
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
    public String findStudyMemberRoleByStudyNo(int studyNo, String email) {
        return studyMapper.findStudyMemberRoleByStudyNo(studyNo, email);
    }

    @Override
    public void modifyStudy(Map<String, String> studyDTO) {
        studyMapper.modifyStudy(studyDTO);
    }

    @Override
    public void deleteStudyByStudyNo(int studyNo) {
        studyMapper.deleteStudyByStudyNo(studyNo);
    }

    @Override
    public List<StudyMemberDTO> getMystudyListByEmail(String email) {
        return studyMapper.getMystudyListByEmail(email);
    }

    @Override
    public void updateState(int studyNo,String studyState) {
        studyMapper.updateState(studyNo,studyState);
    }

    @Override
    public Map<String, Integer> getStudyCurrentSituation(String mentee, int studyNo) {
        int total = studyMapper.getStudyAllTaskCount(studyNo);
        int num = studyMapper.getStudyMyTaskCount(studyNo, mentee);

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("total", total);
        map.put("num", num);

        return map;
    }
}
