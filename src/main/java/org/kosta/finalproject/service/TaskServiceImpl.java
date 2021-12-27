package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.model.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getTaskDetailByTaskNo(int taskNo) {
        return taskMapper.getTaskDetailByTaskNo(taskNo);
    }

    @Override
    public List<UploadFile> findImagesById(int taskNo) {
        List<UploadFile> attachFiles = taskMapper.findFilesById("FILE", taskNo);
        return attachFiles;
    }

    @Override
    public List<UploadFile> findFilesById(int taskNo) {
        List<UploadFile> imgFiles = taskMapper.findFilesById("IMG", taskNo);
        return imgFiles;
    }
}
