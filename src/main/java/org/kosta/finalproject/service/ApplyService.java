package org.kosta.finalproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApplyService {

    List<Map<String, Object>> getAlarmList();

    void applyRefuse(int applyNo);

    void applyAccept(Map<String, Object> param);

    List<Map<String, Object>> requestedApplyList(String email);

    List<Map<String, Object>> isStudyLeader(String email);

    int registerApplyStudy(HashMap<String, String> jsonData);

}
