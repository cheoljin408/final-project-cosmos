package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitCommentDTO {

    private int submitNo;
    private String submitContent;
    private String submitRegdate;
    private String submitUploadFileName;
    private String submitStoreFileName;
    private int taskNo;
    private String email;
    private int studyNo;

}
