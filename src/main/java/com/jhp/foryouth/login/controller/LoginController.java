package com.jhp.foryouth.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    public String loginMain(@RequestParam(value = "error", required = false) String error, Model model){
        log.info("로그인 페이지");
        model.addAttribute("error", error != null);
        return "login/loginMain";
    }
}
