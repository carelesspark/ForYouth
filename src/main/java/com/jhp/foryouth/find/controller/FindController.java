package com.jhp.foryouth.find.controller;

import com.jhp.foryouth.find.service.FindAuthService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/find")
@Log4j2
@RequiredArgsConstructor
public class FindController {

    private final FindAuthService authService;

    @GetMapping("/findId")
    public void findId(){
        log.info("아이디 찾기 페이지");
    }

    @PostMapping("/auth/user-id")
    public void findUserId(@RequestParam String userName, @RequestParam String userEmail, HttpServletResponse response) throws IOException {
        try {
            boolean result = authService.findUserIdByNameAndEmail(userName, userEmail);
            if(result) {
                response.sendRedirect("/find/findIdFinish");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("<script>alert('해당 정보를 가진 유저가 존재하지 않습니다.');</script>");
                response.sendRedirect("/find/findId");
            }
        } catch (MessagingException e) {
            log.error("이메일 전송 오류", e);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script>alert('이메일 전송 중 오류가 발생했습니다.');</script>");
            response.sendRedirect("/");
        }
    }

    @GetMapping("/findIdFinish")
    public void findIdFinish(){
        log.info("아이디 찾기 성공 페이지");
    }

    @GetMapping("/findPw")
    public void findPw(){
        log.info("비밀번호 찾기 페이지");
    }

    @GetMapping("/resetPw")
    public void resetPw(){
        log.info("비밀번호 초기화 페이지");
    }

    @GetMapping("/findPwFinish")
    public void findPwFinish(){
        log.info("비밀번호 초기화 완료 페이지");
    }

}
