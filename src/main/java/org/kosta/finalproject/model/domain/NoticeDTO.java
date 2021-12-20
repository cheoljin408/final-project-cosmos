package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private int noticeNo;
    private String noticeTitle;
    private String noticeContent;
    private String noticeRegdate;
    private int noticeHits;
    private MemberDTO memberDTO;
    private StudyDTO studyDTO;
    private String address;
}
