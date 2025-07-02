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
public class NaverDTO {
    private Long num;

    private LocalDateTime modDate;

    private LocalDateTime regDate;

    private String birthday;

    private String birthyear;

    private String email;

    private String name;

    private String provider;

    private String mobile;

    private Boolean agreedEventAlarm;
}
