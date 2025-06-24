package com.jhp.foryouth.main.controller;

import com.jhp.foryouth.login.config.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
@Controller
@RequiredArgsConstructor
@Log4j2
public class MainController {

    @GetMapping("/")
    public String mainTest(Model model){
        log.info("메인 페이지");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("pageTitle", "ForYouth 메인 페이지");
        model.addAttribute("cssPath", "/css/main/main.css");

        model.addAttribute("bootstrapCssPath", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css");
        model.addAttribute("bootstrapCssIntegrity", "sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr");
        model.addAttribute("bootstrapCssCrossOrigin", "anonymous");

        model.addAttribute("bootstrapJsPath", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js");
        model.addAttribute("bootstrapJsIntegrity", "sha384-ndDqU0Gzau9qJ1lfW4pNLlhNTkCfHzAVBReH9diLvGRem5+R9g2FzA8ZGN954O5Q");
        model.addAttribute("bootstrapJsCrossOrigin", "anonymous");


        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            model.addAttribute("isLogin", false);
            return "main/main";
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

        return "main/main";
    }
}
