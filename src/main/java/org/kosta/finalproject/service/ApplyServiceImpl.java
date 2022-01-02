package org.kosta.finalproject.service;

import org.kosta.finalproject.model.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public List<Map<String, Object>> getAlarmList(String email) {
        List<Map<String, Object>> alarmList = applyMapper.getAlarmList(email);
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

    @Override
    public List<Map<String, Object>> requestedApplyList(String email) {
        List<Map<String, Object>> requestedApplyList = applyMapper.requestedApplyList(email);
        return requestedApplyList;
    }

    @Override
    public List<Map<String, Object>> isStudyLeader(String email) {
        List<Map<String, Object>> isStudyLeader = applyMapper.isStudyLeader(email);
        return isStudyLeader;
    }

    //스터디 신청
    @Override
    public int registerApplyStudy(HashMap<String, String> jsonData) {
        String StudyNo = jsonData.get("studyNo");
        String applyContent = jsonData.get("applyContent");
        String email = jsonData.get("email");
        if(applyMapper.checkApplyByStudyNoAndEmail(StudyNo,email)==0){ //apply 중 해당 스터디No와 email로 이미 지원된 apply 가 있는 지 확인
            applyMapper.registerApplyStudy(StudyNo,applyContent,email); //해당 이메일과 스터디번호에 신청정보가 없다면 apply 등록
            return 1; //등록을 성공하면 return 1
        }else{
            return 0; //이미 등록되어 실패했다면 return 0
        }
    }
}
