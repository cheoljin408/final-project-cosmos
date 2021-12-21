package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.NoticeDTO;

import java.util.List;

public interface NoticeService {

    List<NoticeDTO> getAllNoticeList(int studyNo);
}
