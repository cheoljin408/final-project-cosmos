package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.StudyDTO;
import java.util.Map;

@Mapper
public interface StudyMapper {

    void registerStudy(StudyDTO studyDTO);

    void registerStudyMemberRole(String email);

    Map<String, Object> getStudyDetailByStudyNo(int studyNO);

    String findStudyMemberRoleByStudyNo(int studyNo, String email);
}
