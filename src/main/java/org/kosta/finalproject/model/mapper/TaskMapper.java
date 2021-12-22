package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.TaskDTO;

import java.util.List;

@Mapper
public interface TaskMapper {
    List<TaskDTO> getRecentTaskList(int studyNo);
}
