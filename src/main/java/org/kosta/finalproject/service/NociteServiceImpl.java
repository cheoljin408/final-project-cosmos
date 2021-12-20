package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NociteServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;

    @Autowired
    public NociteServiceImpl(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

    @Override
    public List<NoticeDTO> getAllNoticeList(int studyNo) {
        return noticeMapper.getAllNoticeList(studyNo);
    }
}
