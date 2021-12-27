package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.model.domain.UploadFile;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<TaskDTO> getRecentTaskList(int studyNo);

    List<TaskDTO> getAllTaskListByStudyNo(int studyNo);

    Map<String, Object> getTaskDetailByTaskNo(int taskNo);

    List<UploadFile> findImagesById(int taskNo);

    List<UploadFile> findFilesById(int taskNo);

    void deleteTask(int taskNo);
}
