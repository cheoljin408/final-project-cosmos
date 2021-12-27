package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getRecentTaskList(int studyNo);

    List<TaskDTO> getAllTaskListByStudyNo(int studyNo);
}
