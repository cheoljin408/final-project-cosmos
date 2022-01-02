package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskFormDTO {

    private String taskTitle;
    private String taskContent;
    private List<MultipartFile> attachFiles;
    private List<MultipartFile> imageFiles;
}
