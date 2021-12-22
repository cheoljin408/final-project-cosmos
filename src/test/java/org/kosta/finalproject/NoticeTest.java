package org.kosta.finalproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NoticeTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    @DisplayName("공지사항 리스트 조회")
    void 공지사항리스트조회(){
        //given
        // noticeNo:1, noticeTitle: 공지사항 제목1, noticeContent: 공지사항 내용 2, studyNo: 108, ...
        int studyNo = 108;
        String email = "nogy21@gmail.com";

        //when
        List<NoticeDTO> noticeList = noticeService.getAllNoticeList(studyNo);

        //then
        for (NoticeDTO noticeDTO : noticeList) {
            System.out.println("noticeDTO = " + noticeDTO);
        }
        assertThat(noticeList.get(0).getMemberDTO().getEmail()).isEqualTo(email);
        assertThat(noticeList.get(0).getNoticeTitle()).isEqualTo("공지사항 제목3");
    }

    @Test
    @DisplayName("공지사항 작성(파일 업로드)")
    void 공지사항작성() {
        //given
        int studyNo = 108;
        String email = "nogy21@gmail.com";

        // noticeService.registerNotice(studyNo, email, );

        //when

        //then
    }

}
