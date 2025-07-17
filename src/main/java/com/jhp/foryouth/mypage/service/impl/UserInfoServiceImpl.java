package com.jhp.foryouth.mypage.service.impl;

import com.jhp.foryouth.user.domain.*;
import com.jhp.foryouth.user.repository.*;
import com.jhp.foryouth.mypage.service.UserInfoService;
import com.jhp.foryouth.user.dto.KakaoDTO;
import com.jhp.foryouth.user.dto.NaverDTO;
import com.jhp.foryouth.user.dto.UserDTO;
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
    private final UserRepository userRepository;
    private final KakaoUserRepository kakaoUserRepository;
    private final NaverUserRepository naverUserRepository;

    private final UserInterestsRepository userInterestsRepository;
    private final UserKakaoInterestsRepository userKakaoInterestsRepository;
    private final UserNaverInterestsRepository userNaverInterestsRepository;

    private final WithdrawUsersRepository withdrawUsersRepository;

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

    @Override
    public void withdraw(String userId, String provider, String email, String reason) {
        if(provider == null) {
            UserAuth userAuth = userAuthRepository.findByUserIdWithUser(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            User user = userAuth.getUser();

            WithdrawUsers entity = valuesToWithdrawEntity(email, user.getUserName(), reason, "normal");
            withdrawUsersRepository.save(entity);

            userAuthRepository.delete(userAuth);
            userInterestsRepository.deleteByUserNum(user.getNum());
            userRepository.delete(user);

        } else if(provider.equals("naver")) {
            AuthNaver naver = naverUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            WithdrawUsers entity = valuesToWithdrawEntity(email, naver.getName(), reason, provider);
            withdrawUsersRepository.save(entity);

            userNaverInterestsRepository.deleteByUserNaverNum(naver.getNum());
            naverUserRepository.delete(naver);

        } else if(provider.equals("kakao")) {
            AuthKakao kakao = kakaoUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            WithdrawUsers entity = valuesToWithdrawEntity(email, kakao.getNickname(), reason, provider);
            withdrawUsersRepository.save(entity);

            userKakaoInterestsRepository.deleteByUserKakaoNum(kakao.getNum());
            kakaoUserRepository.delete(kakao);
        }
    }


}
