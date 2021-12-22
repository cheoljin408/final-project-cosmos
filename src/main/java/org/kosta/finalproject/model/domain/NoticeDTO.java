package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {

    private int noticeNo;
    private String noticeTitle;
    private String noticeContent;
    private String noticeRegdate;
    private int noticeHits;
    private String address;
    private StudyMemberDTO studyMemberDTO;
}
