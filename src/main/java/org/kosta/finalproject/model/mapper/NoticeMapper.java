package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.domain.NoticeFileDTO;
import org.kosta.finalproject.model.domain.UploadFile;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper {

    List<NoticeDTO> getAllNoticeList(int studyNo);

    Map<String, Object> getNoticeDetailByNoticeNo(int noticeNo);

    void updateHits(@Param("studyNo") int studyNo, @Param("noticeNo") int noticeNo);

    void deleteNotice(int noticeNo);

    List<NoticeDTO> getRecentNoticeList(int studyNo);

    int registerNotice(@Param("studyNo")int studyNo, @Param("email")String email, @Param("noticeTitle")String noticeTitle, @Param("noticeContent")String noticeContent);

    void registerAttachFile(@Param("attachFile")UploadFile attachFile, @Param("type")String type, @Param("noticeNo")int noticeNo);

    void registerStoreImage(@Param("storeImage")UploadFile storeImage, @Param("type")String type, @Param("noticeNo")int noticeNo);

    int getNoticeNoWhenRegister();

    List<UploadFile> findFilesById(@Param("fileType") String fileType, @Param("noticeNo") int noticeNo);
}
