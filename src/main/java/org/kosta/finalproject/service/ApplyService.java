package org.kosta.finalproject.service;

import java.util.List;
import java.util.Map;

public interface ApplyService {
    List<Map<String, Object>> getAlarmList();

    void applyRefuse(int applyNo);

    void applyAccept(String email, int applyNo, int studyNo);

    List<Map<String, Object>> requestedApplyList(String email);

}
