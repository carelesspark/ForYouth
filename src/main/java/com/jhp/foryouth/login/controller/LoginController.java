package com.jhp.foryouth.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foryouth")
@Log4j2
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public void login(){
        log.info("로그인 페이지");
    }

    @GetMapping("/join")
    public void join(){
        log.info("회원가입 페이지");
    }

    @GetMapping("/terms_of_use")
    public void terms_of_use(){
        log.info("이용약관 페이지");
    }

    @GetMapping("/terms_of_personal_info")
    public void terms_of_personal_info(){
        log.info("개인정보수집약관 페이지");
    }


}
