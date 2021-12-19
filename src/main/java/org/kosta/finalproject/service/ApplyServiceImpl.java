package org.kosta.finalproject.service;

import org.kosta.finalproject.model.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceImpl implements ApplyService {

    private ApplyMapper applyMapper;

    @Autowired
    public ApplyServiceImpl(ApplyMapper applyMapper) {
        this.applyMapper = applyMapper;
    }

    @Override
    public List<Map<String, Object>> getAlarmList() {
        List<Map<String, Object>> alarmList = applyMapper.getAlarmList();
        return alarmList;
    }

    @Override
    public void applyRefuse(int applyNo) {
        applyMapper.applyRefuse(applyNo);
    }

    @Override
    public void applyAccept(Map<String, Object> param) {
        applyMapper.applyAccept(Integer.parseInt((String)param.get("apply_no")));
        System.out.println("completed");
        applyMapper.insertStudyMember((String)param.get("email"), (String)param.get("study_no"));
    }

    public List<Map<String, Object>> requestedApplyList(String email) {
        List<Map<String, Object>> requestedApplyList = applyMapper.requestedApplyList(email);
        return requestedApplyList;
    }

}