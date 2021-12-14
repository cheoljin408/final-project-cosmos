package org.kosta.finalproject.config.auth;

import lombok.RequiredArgsConstructor;
import org.kosta.finalproject.config.auth.dto.OAuthAttributes;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.MemberDTO;
import org.kosta.finalproject.model.mapper.MemberMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberMapper memberMapper;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        MemberDTO memberDTO = saveOrUpdate(attributes);

        httpSession.setAttribute("member", new SessionMember(memberDTO));

        return new DefaultOAuth2User(
                Collections.singleton(
                        new SimpleGrantedAuthority(memberDTO.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private MemberDTO saveOrUpdate(OAuthAttributes attributes) {
        /*
        MemberDTO memberDTO = memberMapper.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
         */

        MemberDTO memberDTO = memberMapper.findByEmail(attributes.getEmail());

        if(memberDTO == null) {
            memberMapper.registerMember(attributes.toEntity());
        } else {
            memberMapper.updateMember(attributes.toEntity());
        }

        return memberMapper.findByEmail(attributes.getEmail());
    }
}
