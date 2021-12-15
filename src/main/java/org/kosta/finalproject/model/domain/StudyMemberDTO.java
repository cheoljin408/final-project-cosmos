package org.kosta.finalproject.model.domain;

import lombok.Data;

@Data
public class StudyMemberDTO {
    private MemberDTO memberDTO;
    private StudyDTO studyDTO;
    private String studyMemberRole;
}
