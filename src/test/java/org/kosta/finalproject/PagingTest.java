package org.kosta.finalproject;

import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.domain.TaskDTO;
import org.kosta.finalproject.service.PagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PagingTest {

    private final PagingService pagingService;

    @Autowired
    public PagingTest(PagingService pagingService) {
        this.pagingService = pagingService;
    }

    @Test
    void getTotalCountTest() {
        // given
        int count = 13;

        // when
        int totalCount = pagingService.getTotalCountOfStudyList();

        // then
        assertThat(count).isEqualTo(totalCount);
    }

    @Test
    void getNoticeListByPageNo(){
        //given
        int startRowNumber = 1;
        int endRowNumber = 5;
        int studyNo = 25;

        //when
        List<NoticeDTO> noticeList = pagingService.getNoticeListByPageNo(studyNo,startRowNumber,endRowNumber);

        //then
        for (NoticeDTO noticeDTO : noticeList) {
            System.out.println(noticeDTO);
        }

    }

    @Test
    void geTaskListByPageNo(){
        //given
        int startRowNumber = 1;
        int endRowNumber = 40;
        int studyNo = 25;

        //when
        List<TaskDTO> taskList = pagingService.getTaskListByPageNo(studyNo,startRowNumber,endRowNumber);

        //then
        for (TaskDTO taskDTO : taskList) {
            System.out.println(taskDTO);
        }

    }
}
