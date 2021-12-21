package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyMemberMapper {

    List<Map<String, Object>> getStudyNameList(Map<String, Object> emailAndStudyNo);

    StudyMemberDTO getAllStudyInfo(int studyNo);
}
