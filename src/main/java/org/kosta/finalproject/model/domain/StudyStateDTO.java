package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyStateDTO {
    private String studyStateCode;
    private String studyState;
}
