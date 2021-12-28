package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.domain.TaskDTO;

import java.util.List;

public interface PagingService {

    int getTotalCountOfStudyList();

    List<StudyMemberDTO> getStudyListByPageNo(int startRowNumber, int endRowNumber);

    int getTotalCountOfStudyListByCategory(String category);

    List<StudyMemberDTO> getStudyListByCategoryAndPageNo(String category, int startRowNumber, int endRowNumber);

    int getTotalCountOfStudyListBySearch(String search);

    List<StudyMemberDTO> getStudyListBySearch(String search, int startRowNumber, int endRowNumber);

    int getTotalCountOfNoticeList(int studyNo);

    List<NoticeDTO> getNoticeListByPageNo(int studyNo,int startRowNumber, int endRowNumber);

    int getTotalCountOfTaskList(int studyNo);

    List<TaskDTO> getTaskListByPageNo(int studyNo, int startRowNumber, int endRowNumber);

}
