package org.kosta.finalproject.model.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class UploadFile {

    private String uploadFileName; // 업로드한 파일 이름
    private String storeFileName; // 시스템에 저장되는 이름

    @Autowired
    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
