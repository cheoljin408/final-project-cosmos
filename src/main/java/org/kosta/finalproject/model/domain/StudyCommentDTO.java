package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyCommentDTO {
    private int studyCommentNo;
    private String studyCommentContent;
    private String StudyCommentRegdate;
    private MemberDTO memberDTO;
    private StudyDTO studyDTO;
}
