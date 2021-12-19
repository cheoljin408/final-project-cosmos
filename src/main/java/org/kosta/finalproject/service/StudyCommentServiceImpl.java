package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.model.mapper.StudyCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudyCommentServiceImpl implements StudyCommentService {

    private final StudyCommentMapper studyCommentMapper;

    @Autowired
    public StudyCommentServiceImpl(StudyCommentMapper studyCommentMapper) {
        this.studyCommentMapper = studyCommentMapper;
    }

    @Override
    public List<StudyCommentDTO> getAllStudyCommentList(int studyNo) {
        return studyCommentMapper.getAllStudyCommentList(studyNo);
    }

    @Override
    public void registerStudyComment(Map<String, Object> jsonData) {
        studyCommentMapper.registerStudyComment(jsonData);
    }

    @Override
    public void updateStudyComment(Map<String, Object> jsonData) {
        studyCommentMapper.updateStudyComment(jsonData);
    }

    @Override
    public Map<String, Object> getStudyCommentByStudyCommentNo(int studyCommentNo) {
        Map<String, Object> studyComment = studyCommentMapper.getStudyCommentByStudyCommentNo(studyCommentNo);
        return studyComment;
    }
}
