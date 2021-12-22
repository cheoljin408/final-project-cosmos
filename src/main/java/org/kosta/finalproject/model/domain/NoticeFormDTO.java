package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeFormDTO {

    private String noticeTitle;
    private String noticeContent;
    private List<MultipartFile> attachFiles;
    private List<MultipartFile> imageFiles;

}
