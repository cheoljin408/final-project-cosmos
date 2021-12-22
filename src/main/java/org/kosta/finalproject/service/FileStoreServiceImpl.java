package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 멀티파트 파일을 서버에 저장하는 역할
@Component
public class FileStoreServiceImpl implements FileStoreService {

    @Value("${file.dir}")
    private String fileDir;

    @Override
    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    // 여러 이미지 저장
    @Override
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                // NoticeFileDTO NoticeFileDTO = storeFile(multipartFile);
                // storeFileResult.add(NoticeFileDTO);
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    // 스프링이 제공해주는 MultipartFile을 받아서 파일을 저장한 후 업로드 파일로 반환
    @Override
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        // 사용자가 업로드한 원본 파일 이름
        String originalFilename = multipartFile.getOriginalFilename();
        // 저장되는 파일명
        String storeFileName = createStoreFileName(originalFilename);
        // 디렉토리에 파일 합쳐진 것이 파일로 생성되고 지정한 파일에 저장
        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        // 테스트코드 작성하면 더 좋을 듯
        return new UploadFile(originalFilename, storeFileName);
    }

    // 서버 내부에서 관리하는 파일명. 유일한 이름을 생성하는 UUID 를 사용
    public String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        // 서버에 저장하는 파일명
        String uuid = UUID.randomUUID().toString();
        // "qwe-qwe-123-qwe-qwe"와 같이 저장됨. 여기에 확장자를 추가하기 위해 오리지널 파일명을 식별할 ext를 추가
        return uuid + "." + ext;
    }

    // 확장자 별도 추출
    public String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
