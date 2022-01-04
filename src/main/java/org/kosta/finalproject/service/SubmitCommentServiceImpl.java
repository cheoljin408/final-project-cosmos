package org.kosta.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;
import org.kosta.finalproject.model.mapper.SubmitCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class SubmitCommentServiceImpl implements SubmitCommentService {

    private final SubmitCommentMapper submitCommentMapper;

    @Autowired
    public SubmitCommentServiceImpl(SubmitCommentMapper submitCommentMapper) {
        this.submitCommentMapper = submitCommentMapper;
    }

    @Override
    public List<HashMap<String, String>> getAllSubmitComment(int studyNo, int taskNo) {
        return submitCommentMapper.getAllSubmitComment(studyNo, taskNo);
    }

    @Override
    public void deleteTaskComment(int submitNo) {
        log.debug("submitNo = {}", submitNo);
        submitCommentMapper.deleteTaskComment(submitNo);
    }

    @Override
    public void registerSubmitComment(SubmitCommentDTO submitCommentDTO) {
        submitCommentMapper.registerSubmitComment(submitCommentDTO);
    }

    @Override
    public void updateSubmitComment(SubmitCommentDTO submitCommentDTO) {
        submitCommentMapper.updateSubmitComment(submitCommentDTO);
    }

}
