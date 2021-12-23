package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    List<NoticeDTO> getAllNoticeList(int studyNo);

    Map<String, Object> getNoticeDetailByNoticeNo(int noticeNo);

    void updateHits(int StudyNo, int noticeNo);

    void deleteNotice(int noticeNo);

    List<NoticeDTO> getRecentNoticeList(int studyNo);

    int registerNotice(int studyNo, String email, NoticeFormDTO noticeFormDTO, List<UploadFile> attachFiles, List<UploadFile> uploadFiles);

    List<UploadFile> findFilesById(int noticeNo);

    List<UploadFile> findImagesById(int noticeNo);

}
