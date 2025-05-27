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
public class UserDTO {

    private Long num;

    private LocalDateTime modDate;

    private LocalDateTime regDate;

    private String userId;

    private String userPw;

    private String userPhone;

    private String userName;

    private String userBirth;

    private String userEmail;

    private Boolean agreedEventAlarm;
}
