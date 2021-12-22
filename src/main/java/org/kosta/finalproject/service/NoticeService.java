package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;

import java.util.List;

public interface NoticeService {

    List<NoticeDTO> getAllNoticeList(int studyNo);

    int registerNotice(int studyNo, String email, NoticeFormDTO noticeFormDTO, List<UploadFile> attachFiles, List<UploadFile> uploadFiles);

}
