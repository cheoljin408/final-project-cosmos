package org.kosta.finalproject.service;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.model.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    public List<NoticeDTO> getAllNoticeList(int studyNo) {
        return noticeMapper.getAllNoticeList(studyNo);
    }

    @Override
    public Map<String, Object> getNoticeDetailByNoticeNo(int noticeNo) {
        return noticeMapper.getNoticeDetailByNoticeNo(noticeNo);
    }

    @Override
    public void updateHits(int studyNo, int noticeNo) {
        noticeMapper.updateHits(studyNo, noticeNo);
        System.out.println("studyNo = " + studyNo + ", noticeNo = " + noticeNo);
        System.out.println("NoticeServiceImpl.updateHits");
    }

    @Override
    public void deleteNotice(int noticeNo) {
        noticeMapper.deleteNoticeFileByNoticeNo(noticeNo);
        noticeMapper.deleteNotice(noticeNo);
    }

    @Override
    public List<NoticeDTO> getRecentNoticeList(int studyNo) {
        List<NoticeDTO> recentNoticeList = noticeMapper.getRecentNoticeList(studyNo);
        return recentNoticeList;
    }

    @Transactional
    public int registerNotice(int studyNo, String email, NoticeFormDTO noticeFormDTO, List<UploadFile> attachFiles, List<UploadFile> storeImageFiles) {
        // 1. 게시물 등록(게시물 번호 생성)
        noticeMapper.registerNotice(studyNo, email, noticeFormDTO.getNoticeTitle(), noticeFormDTO.getNoticeContent());
        // 게시물 번호 조회
        int noticeNo = noticeMapper.getNoticeNoWhenRegister();
        System.out.println("noticeNo: " + noticeNo);

        // 2. 파일 저장
        if(attachFiles.size() != 0) {
            for (UploadFile attachFile : attachFiles) {
                noticeMapper.registerAttachFile(attachFile, "FILE", noticeNo);
            }
            System.out.println("파일 저장 완료");
        }

        // 3. 이미지 저장
        if(storeImageFiles.size() != 0) {
            for (UploadFile storeImage : storeImageFiles) {
                noticeMapper.registerStoreImage(storeImage, "IMG", noticeNo);
            }
            System.out.println("이미지 저장 완료");
        }

        return noticeNo;

    }

    @Override
    public List<UploadFile> findFilesById(int noticeNo) {
        List<UploadFile> attachFiles = noticeMapper.findFilesById("FILE", noticeNo);
        return attachFiles;
    }

    @Override
    public List<UploadFile> findImagesById(int noticeNo) {
        List<UploadFile> imgFiles = noticeMapper.findFilesById("IMG", noticeNo);
        return imgFiles;
    }

    @Override
    public void updateNoticeByNoticeNo(String noticeTitle, String noticeContent, int noticeNo){
        log.info("title, content, noticeno = {}, {}, {}", noticeTitle, noticeContent, noticeNo);
        noticeMapper.updateNoticeByNoticeNo(noticeTitle, noticeContent, noticeNo);
    }

    @Override
    public void deleteNoticeFileByNoticeNo(int noticeNo) {
        noticeMapper.deleteNoticeFileByNoticeNo(noticeNo);
    }

    @Override
    public void registerNoticeFiles(int noticeNo, NoticeFormDTO noticeFormDTO, List<UploadFile> attachFiles, List<UploadFile> storeImageFiles) {
        // 2. 파일 저장
        if(attachFiles.size() != 0) {
            for (UploadFile attachFile : attachFiles) {
                noticeMapper.registerAttachFile(attachFile, "FILE", noticeNo);
            }
            System.out.println("파일 저장 완료");
        }

        // 3. 이미지 저장
        if(storeImageFiles.size() != 0) {
            for (UploadFile storeImage : storeImageFiles) {
                noticeMapper.registerStoreImage(storeImage, "IMG", noticeNo);
            }
            System.out.println("이미지 저장 완료");
        }
    }
}