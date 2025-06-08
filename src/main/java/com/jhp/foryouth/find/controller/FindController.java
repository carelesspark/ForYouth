package com.jhp.foryouth.find.controller;

import com.jhp.foryouth.find.dto.FindPwDTO;
import com.jhp.foryouth.find.service.FindAuthService;
import com.jhp.foryouth.find.service.ResetPwService;
import com.jhp.foryouth.user.dto.UserDTO;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/find")
@Log4j2
@RequiredArgsConstructor
public class FindController {

    private final FindAuthService authService;
    private final ResetPwService resetPwService;

    @GetMapping("/findId")
    public void findId(){
        log.info("아이디 찾기 페이지");
    }

    @PostMapping("/auth/user-id")
    public String findUserId(@RequestParam String userName, @RequestParam String userEmail, RedirectAttributes redirectAttributes) throws IOException {
        try {
            boolean result = authService.findUserIdByNameAndEmail(userName, userEmail);
            if(result) {
                return "redirect:/find/findIdFinish";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "해당 정보를 가진 유저가 존재하지 않습니다.");
                return "redirect:/find/findId";
            }
        } catch (MessagingException e) {
            log.error("이메일 전송 오류", e);
            redirectAttributes.addFlashAttribute("errorMessage", "이메일 전송 중 오류가 발생했습니다.");
            return "redirect:/";
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
    public String resetPw(HttpSession httpSession, Model model){
        log.info("비밀번호 초기화 페이지");
        String userId = (String) httpSession.getAttribute("userId");
        if(userId == null) {
            model.addAttribute("error", "잘못된 접근입니다.");
            model.addAttribute("error_subtitle", "인증되지 않은 접근이므로 해당 페이지로 이동할 수 없습니다.");
            return "error/error";
        }

        model.addAttribute("userId", userId);
        return "find/resetPw";
    }

    @PostMapping("/reset-password")
    public String resetPassword(HttpSession httpSession, FindPwDTO dto, RedirectAttributes redirectAttributes) {
        try {
            boolean result = resetPwService.updatePw(dto);
            if(result) {
                httpSession.removeAttribute("userId");
                return "redirect:/find/findPwFinish";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "비밀번호 초기화 설정에 오류가 발생했습니다.");
                return "redirect:/find/resetPw";
            }
        } catch (Exception e){
            log.error("서버 오류", e);
            redirectAttributes.addFlashAttribute("errorMessage", "서버 오류가 발생했습니다.");
            return "redirect:/";
        }
    }

    @GetMapping("/findPwFinish")
    public void findPwFinish(){
        log.info("비밀번호 초기화 완료 페이지");
    }

}
