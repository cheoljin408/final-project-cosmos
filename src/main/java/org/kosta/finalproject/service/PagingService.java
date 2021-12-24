package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;

public interface PagingService {

    int getTotalCountOfStudyList();

    List<StudyMemberDTO> getStudyListByPageNo(int startRowNumber, int endRowNumber);

    int getTotalCountOfStudyListByCategory(String category);

    List<StudyMemberDTO> getStudyListByCategoryAndPageNo(String category, int startRowNumber, int endRowNumber);

    int getTotalCountOfStudyListBySearch(String search);

    List<StudyMemberDTO> getStudyListBySearch(String search, int startRowNumber, int endRowNumber);
}
