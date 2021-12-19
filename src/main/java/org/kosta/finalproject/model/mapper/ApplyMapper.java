package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {
    List<Map<String, Object>> getAlarmList();
    void applyRefuse(int applyNo);
    void applyAccept(int applyNo);
    void insertStudyMember(String email, int studyNo);
    List<Map<String, Object>> requestedApplyList(String email);
}
