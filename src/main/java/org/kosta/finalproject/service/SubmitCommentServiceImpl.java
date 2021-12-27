package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.SubmitCommentDTO;
import org.kosta.finalproject.model.mapper.SubmitCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmitCommentServiceImpl implements SubmitCommentService {

    private final SubmitCommentMapper submitCommentMapper;

    @Autowired
    public SubmitCommentServiceImpl(SubmitCommentMapper submitCommentMapper) {
        this.submitCommentMapper = submitCommentMapper;
    }

    @Override
    public void deleteTaskComment(int submitNo) {
        submitCommentMapper.deleteTaskComment(submitNo);
    }

    @Override
    public List<SubmitCommentDTO> getAllTaskCommentList(int taskNo) {
        return submitCommentMapper.getAllTaskCommentList(taskNo);
    }
}
