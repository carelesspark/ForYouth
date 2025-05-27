package com.jhp.foryouth.join.controller;

import com.jhp.foryouth.join.service.JoinService;
import com.jhp.foryouth.user.dto.UserDTO;
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

@Controller
@RequestMapping("/join")
@Log4j2
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/joinTermsOfUse")
    public String joinTermsOfUse(){
        log.info("회원가입 약관 동의 페이지");

        return "join/joinTermsOfUse";
    }

    @PostMapping("/joinTermsOfUse_check")
    public String joinTermsOfUse_check(@RequestParam(defaultValue = "false") boolean agreedEventAlarm, HttpSession session){
        log.info("선택 체크 값 확인");
        session.setAttribute("agreedEventAlarm", agreedEventAlarm);
        return "redirect:/join/joinDetail";
    }

    @GetMapping("/joinDetail")
    public String joinDetail(HttpSession httpSession, Model model){
        Boolean agreedEventAlarm = (Boolean)httpSession.getAttribute("agreedEventAlarm");
        log.info("회원가입 상세 페이지, 동의 여부 : {}", agreedEventAlarm);
        model.addAttribute("agreedEventAlarm", agreedEventAlarm);

        return "join/joinDetail";
    }

    @PostMapping("/join_new")
    public String join_new(UserDTO userDTO, Model model){
        log.info("회원가입 진행");
        try {
            joinService.join(userDTO);
            return "redirect:/join/joinFinish";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "예기치 않은 오류가 발생했습니다.");
            model.addAttribute("error_subtitle", "메인 페이지로 돌아가 다시 시도해주세요:)");
            return "error/error";
        }
    }

    @GetMapping("/joinFinish")
    public String joinFinish(HttpSession httpSession){
        log.info("회원가입 완료 페이지");
        httpSession.removeAttribute("agreedEventAlarm");

        return "join/joinFinish";
    }

}
