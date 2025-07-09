package com.jhp.foryouth.mypage.controller;

import com.jhp.foryouth.login.config.CustomUserDetails;
import com.jhp.foryouth.mypage.service.MarketingBtnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MarketingBtnController {

    private final MarketingBtnService marketingBtnService;

    @PostMapping("/api/user/marketing-alarm")
    public ResponseEntity<?> changeMarketingBtn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        String email = null;
        String userId = authentication.getName();
        String provider = null;

        if(principal instanceof CustomUserDetails customUserDetails) {
            email = customUserDetails.getEmail();
        } else if(authentication instanceof OAuth2AuthenticationToken authenticationToken) {
            OAuth2User oauth2User = (OAuth2User) principal;
            email = (String) oauth2User.getAttributes().get("email");
            provider = authenticationToken.getAuthorizedClientRegistrationId();
        }

        marketingBtnService.updateUsersMarketing(userId, provider, email);

        return ResponseEntity.ok(Map.of("success", true));
    }
}
