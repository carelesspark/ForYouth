package com.jhp.foryouth.join.service.impl;

import com.jhp.foryouth.join.repository.JoinAuthKakaoRepository;
import com.jhp.foryouth.join.repository.JoinAuthNaverRepository;
import com.jhp.foryouth.user.domain.AuthKakao;
import com.jhp.foryouth.user.domain.AuthNaver;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class OAuthJoinService extends DefaultOAuth2UserService {

    private final JoinAuthKakaoRepository joinAuthKakaoRepository;
    private final JoinAuthNaverRepository joinAuthNaverRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        String provider = request.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());

        if(provider.equals("kakao")) {
            Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
            String email = (String)kakaoAccount.get("email");
            String nickname = (String)((Map<String, Object>) kakaoAccount.get("profile")).get("nickname");

            attributes.put("email", email);

            AuthKakao authKakao = joinAuthKakaoRepository.findByEmail(email).orElseGet(() -> {
                return AuthKakao.builder()
                        .email(email)
                        .nickname(nickname)
                        .provider(provider)
                        .build();
            });

            joinAuthKakaoRepository.save(authKakao);

            return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), attributes, "email");
        } else if(provider.equals("naver")) {
            Map<String, Object> naverAccount = (Map<String, Object>)attributes.get("response");
            String email = (String)naverAccount.get("email");
            String name = (String)naverAccount.get("name");
            String birthday = (String)naverAccount.get("birthday");
            String birthyear = (String)naverAccount.get("birthyear");
            String mobile = (String)naverAccount.get("mobile");

            attributes.put("email", email);

            AuthNaver authNaver = joinAuthNaverRepository.findByEmail(email).orElseGet(() -> {
                return AuthNaver.builder()
                        .email(email)
                        .name(name)
                        .birthday(birthday)
                        .provider(provider)
                        .birthyear(birthyear)
                        .mobile(mobile)
                        .build();
            });

            joinAuthNaverRepository.save(authNaver);

            return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), attributes, "email");
        } else {
            System.out.print("로그인 실패");
            throw new IllegalArgumentException("지원하지 않는 로그인 제공자입니다.");
        }


    }


}
