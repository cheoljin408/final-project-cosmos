package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {

    List<Map<String, Object>> getAlarmList(@Param("email") String email);

    void applyRefuse(int applyNo);

    void applyAccept(int applyNo);
  
    void insertStudyMember(@Param("email")String email, @Param("studyNo")String studyNo);

    List<Map<String, Object>> requestedApplyList(String email);

    void registerApplyStudy(@Param("studyNo") String studyNo, @Param("applyContent")String applyContent, @Param("email")String email);

    int checkApplyByStudyNoAndEmail(@Param("studyNo")String studyNo, @Param("email")String email);

    List<Map<String, Object>> isStudyLeader(String email);
}
