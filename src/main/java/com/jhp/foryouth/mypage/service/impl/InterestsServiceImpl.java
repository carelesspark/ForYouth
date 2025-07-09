package com.jhp.foryouth.mypage.service.impl;

import com.jhp.foryouth.mypage.repository.UserInterestsRepository;
import com.jhp.foryouth.mypage.repository.UserKakaoInterestsRepository;
import com.jhp.foryouth.mypage.repository.UserNaverInterestsRepository;
import com.jhp.foryouth.mypage.service.InterestsService;
import com.jhp.foryouth.user.domain.*;
import com.jhp.foryouth.user.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class InterestsServiceImpl implements InterestsService {

    private final KakaoUserRepository kakaoUserRepository;
    private final UserKakaoInterestsRepository kakaoInterestsRepository;

    private final NaverUserRepository naverUserRepository;
    private final UserNaverInterestsRepository naverInterestsRepository;

    private final UserAuthRepository userAuthRepository;
    private final UserInterestsRepository userInterestsRepository;

    @Transactional
    @Override
    public void updateUserInterests(String userId, String provider, String email, String interests) {
        if(provider == null) {
            UserAuth userAuth = userAuthRepository.findByUserIdWithUser(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            Optional<UserInterests> existingInterests = userInterestsRepository.findByUserNum(userAuth.getUser().getNum());

            if(existingInterests.isPresent()) {
                UserInterests userInterests = existingInterests.get();
                userInterests.setInterests(interests);
                userInterestsRepository.save(userInterests);
            } else {
                UserInterests userInterests = new UserInterests();
                userInterests.setUser(userAuth.getUser());
                userInterests.setInterests(interests);
                userInterestsRepository.save(userInterests);
            }
        } else if(provider.equals("naver")) {
            AuthNaver naver = naverUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            Optional<UserNaverInterests> existingInterests = naverInterestsRepository.findByNaver_Num(naver.getNum());

            if(existingInterests.isPresent()) {
                UserNaverInterests userNaverInterests = existingInterests.get();
                userNaverInterests.setInterests(interests);
                naverInterestsRepository.save(userNaverInterests);
            } else {
                UserNaverInterests userNaverInterests = new UserNaverInterests();
                userNaverInterests.setNaver(naver);
                userNaverInterests.setInterests(interests);
                naverInterestsRepository.save(userNaverInterests);
            }
        } else if(provider.equals("kakao")) {
            AuthKakao kakao = kakaoUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            Optional<UserKakaoInterests> existingInterests = kakaoInterestsRepository.findByKakao_Num(kakao.getNum());

            if(existingInterests.isPresent()) {
                UserKakaoInterests userKakaoInterests = existingInterests.get();
                userKakaoInterests.setInterests(interests);
                kakaoInterestsRepository.save(userKakaoInterests);
            } else {
                UserKakaoInterests userKakaoInterests = new UserKakaoInterests();
                userKakaoInterests.setKakao(kakao);
                userKakaoInterests.setInterests(interests);
                kakaoInterestsRepository.save(userKakaoInterests);
            }
        }
    }

    @Override
    public String getUserInterests(String userId, String provider, String email) {
        if(provider == null) {
            UserAuth userAuth = userAuthRepository.findByUserIdWithUser(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            Optional<UserInterests> existingInterests = userInterestsRepository.findByUserNum(userAuth.getUser().getNum());

            if(existingInterests.isPresent()) {
                if(existingInterests.get().getInterests().isEmpty()) {
                    return "찾고 있는 중✨";
                } else {
                    return existingInterests.get().getInterests();
                }
            } else {
                return "찾고 있는 중✨";
            }
        } else if(provider.equals("naver")) {
            AuthNaver naver = naverUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            Optional<UserNaverInterests> existingInterests = naverInterestsRepository.findByNaver_Num(naver.getNum());

            if(existingInterests.isPresent()) {
                if(existingInterests.get().getInterests().isEmpty()) {
                    return "찾고 있는 중✨";
                } else {
                    return existingInterests.get().getInterests();
                }
            } else {
                return "찾고 있는 중✨";
            }
        } else if(provider.equals("kakao")) {
            AuthKakao kakao = kakaoUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            Optional<UserKakaoInterests> existingInterests = kakaoInterestsRepository.findByKakao_Num(kakao.getNum());

            if(existingInterests.isPresent()) {
                if(existingInterests.get().getInterests().isEmpty()) {
                    return "찾고 있는 중✨";
                } else {
                    return existingInterests.get().getInterests();
                }
            } else {
                return "찾고 있는 중✨";
            }
        }
        return "알 수 없는 오류가 발생했습니다.";
    }
}
