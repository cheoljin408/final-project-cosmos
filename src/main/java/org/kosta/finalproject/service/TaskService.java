package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.model.domain.TaskFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getRecentTaskList(int studyNo);

    //과제 공지 등록
    int registerTask(int studyNo, String email, TaskFormDTO taskFormDTO, List<UploadFile> attachFiles, List<UploadFile> uploadFiles);
}
