package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.model.domain.TaskFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.model.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<TaskDTO> getAllTaskListByStudyNo(int studyNo) {
        return taskMapper.getAllTaskListByStudyNo(studyNo);
    }

    @Override
    public Map<String, Object> getTaskDetailByTaskNo(int taskNo) {
        return taskMapper.getTaskDetailByTaskNo(taskNo);
    }

    @Override
    public List<UploadFile> findImagesById(int taskNo) {
        List<UploadFile> imgFiles = taskMapper.findFilesById("IMG", taskNo);
        return imgFiles;
    }

    @Override
    public List<UploadFile> findFilesById(int taskNo) {
        List<UploadFile> attachFiles = taskMapper.findFilesById("FILE", taskNo);
        return attachFiles;
    }

    @Override
    public void updateTaskByTaskNo(String taskTitle, String taskContent, int taskNo){
        //log.info("title, content, noticeno = {}, {}, {}", taskTitle, taskContent, taskNo);
        taskMapper.updateTaskByTaskNo(taskTitle, taskContent, taskNo);
    }

    @Override
    public void deleteTaskFileByTaskNo(int taskNo) {
        taskMapper.deleteTaskFileByTaskNo(taskNo);
    }

    @Override
    public void deleteTask(int taskNo) {
        taskMapper.deleteTaskFileByTaskNo(taskNo);
        taskMapper.deleteTask(taskNo);
    }

    @Transactional
    @Override
    public int registerTask(int studyNo, String email, TaskFormDTO taskFormDTO, List<UploadFile> attachFiles, List<UploadFile> storeImageFiles) {
        // 1. 게시물 등록(게시물 번호 생성)
        taskMapper.registerTask(studyNo, email, taskFormDTO.getTaskTitle(), taskFormDTO.getTaskContent());
        // 게시물 번호 조회
        int taskNo = taskMapper.getTaskNoWhenRegister();
        System.out.println("taskNo: " + taskNo);

        // 2. 파일 저장
        if (attachFiles.size() != 0) {
            for (UploadFile attachFile : attachFiles) {
                taskMapper.registerAttachFile(attachFile, "FILE", taskNo);
            }
            System.out.println("파일 저장 완료");
        }

        // 3. 이미지 저장
        if (storeImageFiles.size() != 0) {
            for (UploadFile storeImage : storeImageFiles) {
                taskMapper.registerStoreImage(storeImage, "IMG", taskNo);
            }
            System.out.println("이미지 저장 완료");
        }

        return taskNo;
    }

    @Override
    public void registerTaskFiles(int taskNo, TaskFormDTO taskFormDTO, List<UploadFile> attachFiles, List<UploadFile> storeImageFiles) {
        // 2. 파일 저장
        if (attachFiles.size() != 0) {
            for (UploadFile attachFile : attachFiles) {
                taskMapper.registerAttachFile(attachFile, "FILE", taskNo);
            }
            System.out.println("파일 저장 완료");
        }

        // 3. 이미지 저장
        if (storeImageFiles.size() != 0) {
            for (UploadFile storeImage : storeImageFiles) {
                taskMapper.registerStoreImage(storeImage, "IMG", taskNo);
            }
            System.out.println("이미지 저장 완료");
        }
    }

    @Override
    public UploadFile findFileById(int submitNo) {
        return taskMapper.findFileById(submitNo);
    }
}
