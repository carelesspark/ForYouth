package com.jhp.foryouth.mypage.controller;

import com.jhp.foryouth.login.config.CustomUserDetails;
import com.jhp.foryouth.mypage.dto.QuestionRequest;
import com.jhp.foryouth.mypage.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Log4j2
public class GetQnaController {

    private final QuestionService questionService;

    @GetMapping("/qna")
    public String myPageQnA(Model model) {

        log.info("Q&A 페이지");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "login/loginMain";
        }

        Object principal = authentication.getPrincipal();
        String email = null;
        String provider = null;

        if(principal instanceof CustomUserDetails customUser) {
            email = customUser.getEmail();
        } else if(authentication instanceof OAuth2AuthenticationToken oAuthToken) {
            OAuth2User oAuth2User = (OAuth2User) principal;
            email = (String) oAuth2User.getAttributes().get("email");
            provider = oAuthToken.getAuthorizedClientRegistrationId();
        }

        model.addAttribute("pageTitle", "ForYouth 마이페이지");
        model.addAttribute("cssPath", "/css/mypage/myPageQnA.css");
        model.addAttribute("jsPath", "/js/mypage/myPageQnA.js");

        model.addAttribute("isLogin", true);

        return "mypage/myPageQnA";
    }

    @PostMapping("/qna/send")
    public String updateQuestion(@RequestParam String title, @RequestParam String content, RedirectAttributes redirectAttributes) {
        log.info("문의사항 전송");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "login/loginMain";
        }

        Object principal = authentication.getPrincipal();
        String email = null;
        String provider = null;

        if(principal instanceof CustomUserDetails customUser) {
            email = customUser.getEmail();
        } else if(authentication instanceof OAuth2AuthenticationToken oAuthToken) {
            OAuth2User oAuth2User = (OAuth2User) principal;
            email = (String) oAuth2User.getAttributes().get("email");
            provider = oAuthToken.getAuthorizedClientRegistrationId();
        }

        questionService.saveQuestion(email, provider, title, content);

        redirectAttributes.addFlashAttribute("message", "문의사항이 성공적으로 전송되었습니다.");

        return "redirect:/mypage/qna";
    }

    @GetMapping("/qna/fragments")
    public String getQnaFragment(@RequestParam(defaultValue = "0") int page, Model model) {
        log.info("문의사항 fragments");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "login/loginMain";
        }

        Object principal = authentication.getPrincipal();
        String email = null;
        String provider = null;

        if(principal instanceof CustomUserDetails customUser) {
            email = customUser.getEmail();
        } else if(authentication instanceof OAuth2AuthenticationToken oAuthToken) {
            OAuth2User oAuth2User = (OAuth2User) principal;
            email = (String) oAuth2User.getAttributes().get("email");
            provider = oAuthToken.getAuthorizedClientRegistrationId();
        }

        Page<QuestionRequest> qnaPage = questionService.findQuestionsByUser(email, provider, page);

        model.addAttribute("qnaList", qnaPage.getContent());
        model.addAttribute("qnaPage", qnaPage);

        return "mypage/myPageQnA :: #qnaTableFragment";
    }
}
