package org.kosta.finalproject.model.domain;

import lombok.Data;

@Data
public class StudyCommentDTO {
    private int studyCommentNo;
    private String studyCommentContent;
    private String StudyCommentRegDate;
    private MemberDTO memberDTO;
    private StudyDTO studyDTO;
}
