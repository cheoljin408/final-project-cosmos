package org.kosta.finalproject.service;

import java.util.List;
import java.util.Map;

public interface ApplyService {
    List<Map<String, Object>> getAlarmList();

<<<<<<< HEAD
    List<Map<String, Object>> requestedApplyList(String email);

=======
    void applyRefuse(int applyNo);

    void applyAccept(String email, int applyNo, int studyNo);
>>>>>>> 16c35baacbd4f64a0c8512c8e0e14897beaaae5c

}
