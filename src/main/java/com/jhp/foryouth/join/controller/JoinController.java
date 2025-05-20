package com.jhp.foryouth.join.controller;

import com.jhp.foryouth.join.service.JoinService;
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

    @GetMapping("/joinTermsOfUse")
    public void joinTermsOfUse(){
        log.info("회원가입 약관 동의 페이지");
    }

    @PostMapping("/joinTermsOfUse_check")
    public String joinTermsOfUse_check(@RequestParam(defaultValue = "false") boolean agreedEventAlarm, RedirectAttributes redirectAttributes){
        log.info("선택 체크 값 확인");
        redirectAttributes.addFlashAttribute("agreedEventAlarm", agreedEventAlarm);
        return "redirect:/join/joinDetail";
    }

    @GetMapping("/joinDetail")
    public void joinDetail(Model model){
        Boolean agreedEventAlarm = (Boolean)model.getAttribute("agreedEventAlarm");
        log.info("회원가입 상세 페이지, 동의 여부 : {}", agreedEventAlarm);
    }

    @GetMapping("/joinFinish")
    public void joinFinish(){
        log.info("회원가입 완료 페이지");
    }

}
