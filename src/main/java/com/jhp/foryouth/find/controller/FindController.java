package com.jhp.foryouth.find.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/find")
@Log4j2
@RequiredArgsConstructor
public class FindController {

    @GetMapping("/findId")
    public void findId(){
        log.info("아이디 찾기 페이지");
    }

    @GetMapping("/findIdFinish")
    public void findIdFinish(){
        log.info("아이디 찾기 완료 페이지");
    }

    @GetMapping("/findPw")
    public void findPw(){
        log.info("비밀번호 찾기 페이지");
    }

}
