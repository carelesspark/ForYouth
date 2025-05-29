package com.jhp.foryouth.join.service.impl;

import com.jhp.foryouth.join.repository.JoinAuthKakaoRepository;
import com.jhp.foryouth.user.domain.AuthKakao;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class KakaoJoinService extends DefaultOAuth2UserService {

    private final JoinAuthKakaoRepository joinAuthKakaoRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User kakao = super.loadUser(request);

        String provider = request.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes = kakao.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>)kakaoAccount.get("profile");

        String email = (String)kakaoAccount.get("email");
        String nickname = (String)profile.get("nickname");

        AuthKakao authKakao = joinAuthKakaoRepository.findByEmail(email).orElseGet(() -> {
            return AuthKakao.builder()
                    .email(email)
                    .nickname(nickname)
                    .provider(provider)
                    .build();
        });
        
        log.info("카카오 서비스 로직 실행");

        joinAuthKakaoRepository.save(authKakao);

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), attributes, "email");
    }


}
