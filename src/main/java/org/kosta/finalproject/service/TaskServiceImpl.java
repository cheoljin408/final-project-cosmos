package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.model.domain.TaskFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.model.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public int registerTask(int studyNo, String email, TaskFormDTO taskFormDTO, List<UploadFile> attachFiles, List<UploadFile> storeImageFiles) {
        // 1. 게시물 등록(게시물 번호 생성)
        taskMapper.registerTask(studyNo, email, taskFormDTO.getTaskTitle(), taskFormDTO.getTaskContent());
        // 게시물 번호 조회
        int taskNo = taskMapper.getTaskNoWhenRegister();
        System.out.println("taskNo: " + taskNo);

        // 2. 파일 저장
        if(attachFiles.size() != 0) {
            for (UploadFile attachFile : attachFiles) {
                taskMapper.registerAttachFile(attachFile, "FILE", taskNo);
            }
            System.out.println("파일 저장 완료");
        }

        // 3. 이미지 저장
        if(storeImageFiles.size() != 0) {
            for (UploadFile storeImage : storeImageFiles) {
                taskMapper.registerStoreImage(storeImage, "IMG", taskNo);
            }
            System.out.println("이미지 저장 완료");
        }

        return taskNo;

    }
}
