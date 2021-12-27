package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.model.domain.UploadFile;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<TaskDTO> getRecentTaskList(int studyNo);

    void registerTask(@Param("studyNo")int studyNo, @Param("email")String email, @Param("taskTitle") String taskTitle, @Param("taskContent")String taskContent);

    int getTaskNoWhenRegister();

    void registerAttachFile(@Param("attachFile")UploadFile attachFile, @Param("type")String type, @Param("taskNo")int taskNo);

    void registerStoreImage(@Param("storeImage")UploadFile storeImage, @Param("type") String img, @Param("taskNo") int taskNo);

}
