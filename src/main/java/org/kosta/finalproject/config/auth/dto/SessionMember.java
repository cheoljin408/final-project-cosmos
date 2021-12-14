package org.kosta.finalproject.config.auth.dto;

import lombok.Getter;
import org.kosta.finalproject.model.domain.MemberDTO;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionMember(MemberDTO memberDTO) {
        this.name = memberDTO.getName();
        this.email = memberDTO.getEmail();
        this.picture = memberDTO.getPicture();
    }
}
