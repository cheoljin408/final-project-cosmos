package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyMemberDTO {
    private MemberDTO memberDTO;
    private StudyDTO studyDTO;
    private String studyMemberRole;
}
