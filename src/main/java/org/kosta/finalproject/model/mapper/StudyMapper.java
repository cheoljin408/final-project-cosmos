package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudyMapper {

    List<StudyMemberDTO> getStudyList3();

    List<StudyMemberDTO> getAllStudyList();

    void registerStudy(Map<String, String> studyDTO);

    void registerStudyMemberRole(String email);

    Map<String, Object> getStudyDetailByStudyNo(int studyNO);

    String findStudyMemberRoleByStudyNo(int studyNo, String email);

    void modifyStudy(Map<String, String> studyDTO);

    void deleteStudyByStudyNo(int studyNo);

    List<StudyMemberDTO> getMystudyListByEmail(String email);

    int getTotalCountOfStudyList();

    List<StudyMemberDTO> getStudyListByPageNo(@Param("startRowNumber") int startRowNumber, @Param("endRowNumber") int endRowNumber);

    void updateState(@Param("studyNo")int studyNo,@Param("studyState")String studyState);

    int getTotalCountOfStudyListByCategory(String category);

    List<StudyMemberDTO> getStudyListByCategoryAndPageNo(@Param("category") String category, @Param("startRowNumber") int startRowNumber, @Param("endRowNumber") int endRowNumber);

    int getTotalCountOfStudyListBySearch(String search);

    List<StudyMemberDTO> getStudyListBySearch(@Param("search") String search, @Param("startRowNumber") int startRowNumber, @Param("endRowNumber") int endRowNumber);

    int getStudyAllTaskCount(@Param("studyNo")int studyNo);

    int getStudyMyTaskCount(@Param("studyNo")int studyNo, @Param("email") String mentee);
}
