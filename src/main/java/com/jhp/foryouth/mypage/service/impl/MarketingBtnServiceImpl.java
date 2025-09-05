package com.jhp.foryouth.mypage.service.impl;

import com.jhp.foryouth.mypage.service.MarketingBtnService;
import com.jhp.foryouth.user.entity.AuthKakao;
import com.jhp.foryouth.user.entity.AuthNaver;
import com.jhp.foryouth.user.entity.UserAuth;
import com.jhp.foryouth.user.repository.KakaoUserRepository;
import com.jhp.foryouth.user.repository.NaverUserRepository;
import com.jhp.foryouth.user.repository.UserAuthRepository;
import com.jhp.foryouth.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class MarketingBtnServiceImpl implements MarketingBtnService {

    private final UserAuthRepository userAuthRepository;
    private final UserRepository userRepository;
    private final KakaoUserRepository kakaoUserRepository;
    private final NaverUserRepository naverUserRepository;

    @Override
    public void updateUsersMarketing(String userId, String provider, String email) {
        if(provider == null) {
            UserAuth user = userAuthRepository.findByUserIdWithUser(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            boolean eventStatus = user.getUser().getAgreedEventAlarm();
            eventStatus = !eventStatus;

            userRepository.updateAgreedEventAlarmByNum(eventStatus, user.getUser().getNum());
        } else if(provider.equals("naver")) {
            AuthNaver naver = naverUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            boolean eventStatus = naver.getAgreedEventAlarm();
            eventStatus = !eventStatus;
            naverUserRepository.updateAgreedEventAlarmByNum(eventStatus, email);
        } else if(provider.equals("kakao")) {
            AuthKakao kakao = kakaoUserRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));

            boolean eventStatus = kakao.getAgreedEventAlarm();
            eventStatus = !eventStatus;
            kakaoUserRepository.updateAgreedEventAlarmByNum(eventStatus, email);
        }
    }
}
