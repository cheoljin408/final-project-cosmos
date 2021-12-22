package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.model.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    @Autowired
    public TaskServiceImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDTO> getRecentTaskList(int studyNo) {
        List<TaskDTO> recentTaskList = taskMapper.getRecentTaskList(studyNo);
        return recentTaskList;
    }
}
