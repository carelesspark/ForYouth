package com.jhp.foryouth.mypage.service;

import com.jhp.foryouth.user.domain.AuthKakao;
import com.jhp.foryouth.user.domain.AuthNaver;
import com.jhp.foryouth.user.domain.User;
import com.jhp.foryouth.user.dto.KakaoDTO;
import com.jhp.foryouth.user.dto.NaverDTO;
import com.jhp.foryouth.user.dto.UserDTO;

public interface UserInfoService {

    UserDTO normalUser(String userId);

    UserDTO oAuthUser(String provider, String email);

    default UserDTO entityToDTO(User entity) {
        UserDTO userDTO = UserDTO.builder().num(entity.getNum())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .userPhone(entity.getUserPhone())
                .userName(entity.getUserName())
                .userBirth(entity.getUserBirth())
                .userEmail(entity.getUserEmail())
                .agreedEventAlarm(entity.getAgreedEventAlarm()).build();

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
}
