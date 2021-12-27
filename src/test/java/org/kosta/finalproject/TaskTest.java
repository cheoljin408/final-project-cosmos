package org.kosta.finalproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TaskTest {

    Logger logger = (Logger) LoggerFactory.getLogger(TaskTest.class);

    @Autowired
    private TaskService taskService;

    @Test
    @DisplayName("과제공지 리스트 조회")
    void 과제공지리스트조회() {
        //given
        int studyNo = 108;
        // 108번에 해당하는 과제공지 글은 3개가 등록되어있음

        //when
        List<TaskDTO> tl = taskService.getAllTaskListByStudyNo(studyNo);

        //then
        Assertions.assertThat(tl.size()).isEqualTo(3);
        for (TaskDTO taskDTO : tl) {
            logger.info("taskDTO: {}", taskDTO);
        }

    }
}
