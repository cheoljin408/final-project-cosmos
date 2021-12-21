package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.NoticeDTO;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<NoticeDTO> getRecentNoticeList(int studyNo);

}
