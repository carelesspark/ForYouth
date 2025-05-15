package com.jhp.foryouth.join.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join")
@Log4j2
@RequiredArgsConstructor
public class JoinController {

    @GetMapping("/joinTermsOfUse")
    public void joinTermsOfUse(){
        log.info("회원가입 약관 동의 페이지");
    }

    @GetMapping("/joinDetail")
    public void joinDetail(){
        log.info("회원가입 상세 페이지");
    }

    @GetMapping("/joinFinish")
    public void joinFinish(){
        log.info("회원가입 완료 페이지");
    }

}
