package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.MemberDTO;
import org.kosta.finalproject.model.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
=======
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> 16c35baacbd4f64a0c8512c8e0e14897beaaae5c

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
<<<<<<< HEAD
    public List<Map<String, Object>> requestedApplyList(String email) {
        List<Map<String, Object>> requestedApplyList = applyMapper.requestedApplyList(email);
        return requestedApplyList;
    }
=======
    public void applyRefuse(int applyNo) {
        applyMapper.applyRefuse(applyNo);
    }

    @Override
    public void applyAccept(String email, int applyNo, int studyNo) {
        applyMapper.applyAccept(applyNo);
        applyMapper.insertStudyMember(email, studyNo);
    }

>>>>>>> 16c35baacbd4f64a0c8512c8e0e14897beaaae5c
}
