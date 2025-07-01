package com.jhp.foryouth.mypage.controller;

import com.jhp.foryouth.login.config.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mypage")
@Controller
@Log4j2
@RequiredArgsConstructor
public class MypageController {

    @GetMapping("/user")
    public String mypageMain(Model model) {
        log.info("유저 마이페이지 메인");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("pageTitle", "ForYouth 마이페이지");
        model.addAttribute("cssPath", "/css/mypage/mypageMain.css");

        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "login/loginMain";
        }

        Object principal = authentication.getPrincipal();
        String email = null;

        if(principal instanceof CustomUserDetails customUser) {
            email = customUser.getEmail();
        } else if(principal instanceof OAuth2User oAuth2User) {
            email = (String) oAuth2User.getAttributes().get("email");
        }

        model.addAttribute("isLogin", true);
        model.addAttribute("email", email);

        return "mypage/mypageMain";
    }
}
