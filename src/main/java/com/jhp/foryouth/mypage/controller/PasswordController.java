package com.jhp.foryouth.mypage.controller;

import com.jhp.foryouth.mypage.dto.PasswordRequest;
import com.jhp.foryouth.mypage.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PasswordController {

    private final UserInfoService userInfoService;

    @PostMapping("/api/user/check-password")
    public ResponseEntity<?> checkPassword(@RequestBody PasswordRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = authentication.getName();

        boolean result = userInfoService.checkPassword(userId, request.getOriginalPassword());

        return ResponseEntity.ok().body(Map.of("success", result));
    }

    @PostMapping("/api/user/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = authentication.getName();

        userInfoService.updatePassword(userId, request.getNewPassword());

        return ResponseEntity.ok().body(Map.of("success", true));
    }
}
