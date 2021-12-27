package org.kosta.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;
import org.kosta.finalproject.model.mapper.SubmitCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteTaskComment(int submitNo) {
        log.info("submitNo = {}", submitNo);
        submitCommentMapper.deleteTaskComment(submitNo);
    }

    @Override
    public List<SubmitCommentDTO> getAllTaskCommentList(int taskNo) {
        log.info("taskNo = {}", taskNo);
        return submitCommentMapper.getAllTaskCommentList(taskNo);
    }
}
