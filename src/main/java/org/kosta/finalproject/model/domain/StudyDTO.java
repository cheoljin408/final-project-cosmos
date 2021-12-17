package org.kosta.finalproject.model.domain;

import lombok.*;

@Data
public class StudyDTO {
    private int studyNO;
    private String studyName;
    private String studyDesc;
    private String studyInfo;
    private String studyRegdate;
    private StudyStateDTO studyStateDTO;
    private CategoryTypeDTO categoryTypeDTO;
    private CategoryLangDTO categoryLangDTO;
}
