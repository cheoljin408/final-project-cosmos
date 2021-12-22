package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private int taskNo;
    private String taskTitle;
    private String taskContent;
    private String taskRegdate;
    private StudyMemberDTO studyMemberDTO;
}
