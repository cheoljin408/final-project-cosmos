package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.model.domain.TaskFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<TaskDTO> getRecentTaskList(int studyNo);

    List<TaskDTO> getAllTaskListByStudyNo(int studyNo);

    Map<String, Object> getTaskDetailByTaskNo(int taskNo);

    void updateTaskByTaskNo(String taskTitle, String taskContent, int taskNo);

    List<UploadFile> findImagesById(int taskNo);

    List<UploadFile> findFilesById(int taskNo);

    void deleteTask(int taskNo);
    //과제 공지 등록
    int registerTask(int studyNo, String email, TaskFormDTO taskFormDTO, List<UploadFile> attachFiles, List<UploadFile> uploadFiles);

    void deleteTaskFileByTaskNo(int taskNo);

    void registerTaskFiles(int taskNo, TaskFormDTO taskFormDTO, List<UploadFile> attachFiles, List<UploadFile> storeImageFiles);
}
