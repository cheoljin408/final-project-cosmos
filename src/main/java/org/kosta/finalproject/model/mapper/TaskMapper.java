package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.model.domain.UploadFile;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskMapper {
    List<TaskDTO> getRecentTaskList(int studyNo);

    Map<String, Object> getTaskDetailByTaskNo(int taskNo);

    List<UploadFile> findFilesById(@Param("fileType") String fileType, @Param("taskNo") int taskNo);

    void deleteTaskFileByTaskNo(int taskNo);

    void deleteTask(int taskNo);
}
