package org.kosta.finalproject.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kosta.finalproject.model.domain.MemberDTO;

@Mapper
public interface MemberMapper {
    MemberDTO findByEmail(String email);

    void registerMember(MemberDTO memberDTO);

    void updateMember(MemberDTO memberDTO);
}
