package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApplyMapper {
    //ApplyMapper.xml로 alarm
    List<Map<String, Object>> alarm();
}