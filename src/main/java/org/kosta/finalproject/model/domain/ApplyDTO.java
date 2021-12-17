package org.kosta.finalproject.model.domain;

import lombok.Data;

@Data
public class ApplyDTO {
    private int applyNo;
    private String applyRegdate;
    private String applyContent;
    private MemberDTO memberDTO;
    private StudyDTO studyDTO;
    private ApplyStateDTO applyStateDTO;
}
