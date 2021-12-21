package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.NoticeDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    List<NoticeDTO> getAllNoticeList(int studyNo);

    Map<String, Object> getNoticeDetailByNoticeNo(int noticeNo);

}
