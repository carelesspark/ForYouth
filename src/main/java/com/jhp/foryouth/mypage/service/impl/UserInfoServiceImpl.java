package com.jhp.foryouth.mypage.service.impl;

import com.jhp.foryouth.mypage.service.UserInfoService;
import com.jhp.foryouth.user.domain.AuthKakao;
import com.jhp.foryouth.user.domain.AuthNaver;
import com.jhp.foryouth.user.domain.UserAuth;
import com.jhp.foryouth.user.dto.KakaoDTO;
import com.jhp.foryouth.user.dto.NaverDTO;
import com.jhp.foryouth.user.dto.UserDTO;
import com.jhp.foryouth.user.repository.KakaoUserRepository;
import com.jhp.foryouth.user.repository.NaverUserRepository;
import com.jhp.foryouth.user.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserAuthRepository userAuthRepository;
    private final KakaoUserRepository kakaoUserRepository;
    private final NaverUserRepository naverUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO normalUser(String userId) {
        UserAuth userAuth = userAuthRepository.findByUserIdWithUser(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저 정보를 찾을 수 없습니다."));

        return entityToDTO(userAuth);
    }

    @Override
    public NaverDTO authNaverUser(String email) {
        AuthNaver naver = naverUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저 정보를 찾을 수 없습니다."));

        return entityToNaverDTO(naver);
    }

    @Override
    public KakaoDTO authKakaoUser(String email) {
        AuthKakao kakao = kakaoUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저 정보를 찾을 수 없습니다."));

        return entityToKakaoDTO(kakao);
    }

    @Override
    public boolean checkPassword(String userId, String password) {
        UserAuth userAuth = userAuthRepository.findByUserIdWithUser(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저 정보를 찾을 수 없습니다."));

        return passwordEncoder.matches(password, userAuth.getUserPw());
    }

    @Override
    public void updatePassword(String userId, String password) {
        UserAuth userAuth = userAuthRepository.findByUserIdWithUser(userId)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저 정보를 찾을 수 없습니다."));

        String encodedPassword = passwordEncoder.encode(password);
        userAuth.setUserPw(encodedPassword);

        userAuthRepository.save(userAuth);
    }


}
