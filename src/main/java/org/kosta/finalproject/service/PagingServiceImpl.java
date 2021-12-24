package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagingServiceImpl implements PagingService{

    private final StudyMapper studyMapper;

    @Autowired
    public PagingServiceImpl(StudyMapper studyMapper) {
        this.studyMapper = studyMapper;
    }

    @Override
    public int getTotalCountOfStudyList() {
        int totalCount = studyMapper.getTotalCountOfStudyList();
        return totalCount;
    }

    @Override
    public List<StudyMemberDTO> getStudyListByPageNo(int startRowNumber, int endRowNumber) {
        List<StudyMemberDTO> studyList = studyMapper.getStudyListByPageNo(startRowNumber, endRowNumber);
        return studyList;
    }

    @Override
    public int getTotalCountOfStudyListByCategory(String category) {
        int totalCountOfStudyListByCategory = studyMapper.getTotalCountOfStudyListByCategory(category);
        return totalCountOfStudyListByCategory;
    }

    @Override
    public List<StudyMemberDTO> getStudyListByCategoryAndPageNo(String category, int startRowNumber, int endRowNumber) {
        List<StudyMemberDTO> studyListByCategoryAndPageNo = studyMapper.getStudyListByCategoryAndPageNo(category, startRowNumber, endRowNumber);
        return studyListByCategoryAndPageNo;
    }

    @Override
    public int getTotalCountOfStudyListBySearch(String search) {
        int totalCountOfStudyListBySearch = studyMapper.getTotalCountOfStudyListBySearch(search);
        return totalCountOfStudyListBySearch;
    }

    @Override
    public List<StudyMemberDTO> getStudyListBySearch(String search, int startRowNumber, int endRowNumber) {
        List<StudyMemberDTO> studyListBySearch = studyMapper.getStudyListBySearch(search, startRowNumber, endRowNumber);
        return studyListBySearch;
    }
}
