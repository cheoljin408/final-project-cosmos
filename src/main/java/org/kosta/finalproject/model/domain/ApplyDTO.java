package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDTO {
    private int applyNo;
    private String applyRegdate;
    private String applyContent;
    private MemberDTO memberDTO;
    private StudyDTO studyDTO;
    private ApplyStateDTO applyStateDTO;
}
