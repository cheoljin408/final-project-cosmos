package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.model.mapper.TaskCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCommentServiceImpl implements TaskCommentService {

    private final TaskCommentMapper taskCommentMapper;

    @Autowired
    public TaskCommentServiceImpl(TaskCommentMapper taskCommentMapper) {
        this.taskCommentMapper = taskCommentMapper;
    }

    @Override
    public void deleteTaskComment(int submitNo) {
        taskCommentMapper.deleteTaskComment(submitNo);
    }

    @Override
    public List<StudyCommentDTO> getAllTaskCommentList(int taskNo) {
        return taskCommentMapper.getAllTaskCommentList(taskNo);
    }
}
