package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileStoreService {
    String getFullPath(String filename);

    List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException;

    UploadFile storeFile(MultipartFile multipartFile) throws IOException;

    String createStoreFileName(String originalFilename);

    // 확장자 별도 추출
    String extractExt(String originalFilename);
}
