package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.StudyDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyMapper {

    List<StudyMemberDTO> getStudyList3();

    List<StudyMemberDTO> getAllStudyList();

    List<StudyMemberDTO> getStudyListByStudyNameAndDesc(String searchWord);

    List<StudyMemberDTO> getStudyListByCategory(String categoryVal);

    void registerStudy(Map<String, String> studyDTO);

    void registerStudyMemberRole(String email);

    Map<String, Object> getStudyDetailByStudyNo(int studyNO);

    String findStudyMemberRoleByStudyNo(int studyNo, String email);

    void modifyStudy(Map<String, String> studyDTO);

    void deleteStudyByStudyNo(int studyNo);
}
