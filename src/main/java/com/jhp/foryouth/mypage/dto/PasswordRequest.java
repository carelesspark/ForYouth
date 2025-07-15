package com.jhp.foryouth.mypage.dto;

import lombok.Data;

@Data
public class PasswordRequest {
    private String originalPassword;

    private String newPassword;
}
