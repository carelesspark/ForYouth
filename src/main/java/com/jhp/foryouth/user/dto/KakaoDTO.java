package com.jhp.foryouth.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KakaoDTO {
    private Long num;

    private LocalDateTime modDate;

    private LocalDateTime regDate;

    private String email;

    private String nickname;

    private String provider;

    private Boolean agreedEventAlarm;
}
