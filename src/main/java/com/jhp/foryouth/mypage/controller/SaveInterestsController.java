package com.jhp.foryouth.mypage.controller;

import com.jhp.foryouth.login.config.CustomUserDetails;
import com.jhp.foryouth.mypage.dto.InterestRequest;
import com.jhp.foryouth.mypage.service.InterestsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
public class SaveInterestsController {

    private final InterestsService interestsService;

    @PostMapping("/api/user/interests")
    public ResponseEntity<?> updateUserInterests(@RequestBody InterestRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        String email = null;
        String provider = null;
        String userId = authentication.getName();

        if(principal instanceof CustomUserDetails CustomUserDetails) {
            email = CustomUserDetails.getEmail();
        } else if(authentication instanceof OAuth2AuthenticationToken authenticationToken) {
            OAuth2User oAuth2User = (OAuth2User) principal;
            email = (String) oAuth2User.getAttributes().get("email");
            provider = authenticationToken.getAuthorizedClientRegistrationId();
        }

        interestsService.updateUserInterests(userId, provider, email, request.getInterests());

        return ResponseEntity.ok().body(Map.of("success", true));
    }
}
