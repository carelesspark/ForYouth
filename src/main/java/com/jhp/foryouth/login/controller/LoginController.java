package com.jhp.foryouth.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/loginMain")
    public String loginMain(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "type", required = false) String type, Model model){
        log.info("로그인 페이지");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        }

        if(error != null) {
            if("oauth2".equals(type)) {
                model.addAttribute("errorMessage", "간편 로그인 중 오류가 발생했습니다.");
            } else {
                model.addAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
            }
            model.addAttribute("error", true);
        } else {
            model.addAttribute("error", false);
        }
        return "login/loginMain";
    }
}
