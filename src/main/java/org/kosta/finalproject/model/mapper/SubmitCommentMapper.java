package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;

public interface SubmitCommentMapper {
    void registerSubmitComment(SubmitCommentDTO submitCommentDTO);
}
