package org.kosta.finalproject.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeFileDTO {

    private int noticeFileNo;
    private String noticeUploadFileName;
    private String noticeStoreFileName;
    private String noticeFileType;
    private NoticeDTO noticeDTO;

}
