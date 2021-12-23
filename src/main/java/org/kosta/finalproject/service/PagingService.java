package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;

import java.util.List;

public interface PagingService {

    int getTotalCountOfStudyList();

    List<StudyMemberDTO> getStudyListByPageNo(int startRowNumber, int endRowNumber);

}
