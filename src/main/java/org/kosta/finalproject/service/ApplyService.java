package org.kosta.finalproject.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApplyService {
    //선언부
    List<Map<String, Object>> alarm();

    int registerApplyStudy(HashMap<String, String> jsonData);
}
