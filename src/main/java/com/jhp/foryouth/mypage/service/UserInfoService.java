package com.jhp.foryouth.mypage.service;

import com.jhp.foryouth.user.domain.*;
import com.jhp.foryouth.user.dto.KakaoDTO;
import com.jhp.foryouth.user.dto.NaverDTO;
import com.jhp.foryouth.user.dto.UserDTO;
import lombok.With;

public interface UserInfoService {

    UserDTO normalUser(String userId);

    NaverDTO authNaverUser(String email);

    KakaoDTO authKakaoUser(String email);

    boolean checkPassword(String userId, String password);

    void updatePassword(String userId, String password);

    void withdraw(String userId, String provider, String email, String reason);

    default UserDTO entityToDTO(UserAuth entity) {
        UserDTO userDTO = UserDTO.builder().num(entity.getUser().getNum())
                .regDate(entity.getUser().getRegDate())
                .modDate(entity.getUser().getModDate())
                .userPhone(entity.getUser().getUserPhone())
                .userName(entity.getUser().getUserName())
                .userBirth(entity.getUser().getUserBirth())
                .userEmail(entity.getUser().getUserEmail())
                .agreedEventAlarm(entity.getUser().getAgreedEventAlarm()).build();

        return userDTO;
    }

    default NaverDTO entityToNaverDTO(AuthNaver naver) {
        NaverDTO naverDTO = NaverDTO.builder().num(naver.getNum())
                .modDate(naver.getModDate())
                .regDate(naver.getRegDate())
                .email(naver.getEmail())
                .name(naver.getName())
                .birthday(naver.getBirthday())
                .birthyear(naver.getBirthyear())
                .provider(naver.getProvider())
                .agreedEventAlarm(naver.getAgreedEventAlarm())
                .build();

        return naverDTO;
    }

    default KakaoDTO entityToKakaoDTO(AuthKakao kakao) {
        KakaoDTO kakaoDTO = KakaoDTO.builder().num(kakao.getNum())
                .regDate(kakao.getRegDate())
                .modDate(kakao.getModDate())
                .email(kakao.getEmail())
                .nickname(kakao.getNickname())
                .provider(kakao.getProvider())
                .agreedEventAlarm(kakao.getAgreedEventAlarm())
                .build();

        return kakaoDTO;
    }

    default WithdrawUsers valuesToWithdrawEntity(String email, String name, String reason, String provider) {
        WithdrawUsers entity = WithdrawUsers.builder()
                .email(email)
                .name(name)
                .withdrawReason(reason)
                .provider(provider)
                .build();

        return entity;
    }
}
