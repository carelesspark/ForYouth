package com.jhp.foryouth.find.controller;

import com.jhp.foryouth.find.service.FindAuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class SendEmailController {

    private final FindAuthService findAuthService;

    @PostMapping("/send-code")
    public ResponseEntity<String> sendAuthCode(@RequestParam String userName, @RequestParam String userId, @RequestParam String userEmail) {
        try {
            boolean result = findAuthService.findUserByNameAndIdAndEmail(userName, userId, userEmail);
            if(result) {
                return ResponseEntity.ok("인증 코드가 이메일로 전송되었습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("일치하는 회원 정보가 없습니다.");
            }
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }

    @PostMapping("/check-code")
    public ResponseEntity<String> checkAuthCode(@RequestParam String userId, @RequestParam String authenticationCode, HttpSession httpSession) {
        try {
            boolean result = findAuthService.checkCodeIsRight(userId, authenticationCode);
            if(result) {
                httpSession.setAttribute("userId", userId);
                return ResponseEntity.ok("인증되었습니다!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("인증코드가 일치하지 않습니다. 다시 확인해주세요!");
            }
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }
}
