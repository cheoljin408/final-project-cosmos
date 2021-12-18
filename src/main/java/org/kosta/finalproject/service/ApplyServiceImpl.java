package org.kosta.finalproject.service;

import org.kosta.finalproject.model.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceImpl implements ApplyService {

    private ApplyMapper applyMapper;

    @Autowired
    public ApplyServiceImpl(ApplyMapper applyMapper) {
        this.applyMapper = applyMapper;
    }

    public List<Map<String, Object>> getAlarmList() {
        List<Map<String, Object>> alarmList = applyMapper.getAlarmList();
        return alarmList;
    }

    @Override
    public void applyRefuse(int applyNo) {
        applyMapper.applyRefuse(applyNo);
    }

    @Override
    public void applyAccept(String email, int applyNo, int studyNo) {
        applyMapper.applyAccept(applyNo);
        applyMapper.insertStudyMember(email, studyNo);
    }

}
