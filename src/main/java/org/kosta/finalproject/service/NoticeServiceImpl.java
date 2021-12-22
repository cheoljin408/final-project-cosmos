package org.kosta.finalproject.service;

import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
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
        noticeMapper.deleteNotice(noticeNo);
    }

    @Override
    public List<NoticeDTO> getRecentNoticeList(int studyNo) {
        List<NoticeDTO> recentNoticeList = noticeMapper.getRecentNoticeList(studyNo);
        return recentNoticeList;
    }
}