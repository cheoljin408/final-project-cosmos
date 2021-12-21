package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.NoticeDTO;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    List<NoticeDTO> getAllNoticeList(int studyNo);

    Map<String, Object> getNoticeDetailByNoticeNo(int noticeNo);
}
