package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {
    //ApplyMapper.xml로 alarm
    List<Map<String, Object>> alarm();

    void registerApplyStudy(@Param("studyNo") String studyNo, @Param("applyContent")String applyContent, @Param("email")String email);

    int checkApplyByStudyNoAndEmail(@Param("studyNo")String studyNo, @Param("email")String email);
}
