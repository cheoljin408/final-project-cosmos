package org.kosta.finalproject.service;

import java.util.List;
import java.util.Map;

public interface ApplyService {
    List<Map<String, Object>> getAlarmList();

    List<Map<String, Object>> requestedApplyList(String email);


}
