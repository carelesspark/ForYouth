package com.jhp.foryouth.mypage.controller;

import com.jhp.foryouth.board.service.BoardTotalService;
import com.jhp.foryouth.login.config.CustomUserDetails;
import com.jhp.foryouth.mypage.dto.BookmarkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Log4j2
public class GetBookmarkController {

    private final BoardTotalService boardTotalService;

    @GetMapping("/bookmark")
    public String myBookmark(@RequestParam(defaultValue = "1") int page, Model model) {
        log.info("내가 북마크 해놓은 혜택 게시글");

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

        int zeroBasedPage = Math.max(page - 1, 0);

        Page<BookmarkRequest> bookmark = boardTotalService.getBookmarkByUser(email, provider, null, zeroBasedPage);

        long count = bookmark.getTotalElements();

        model.addAttribute("pageTitle", "ForYouth 마이페이지");
        model.addAttribute("cssPath", "/css/mypage/myBookmark.css");
        model.addAttribute("jsPath", "/js/mypage/postDate.js");

        model.addAttribute("isLogin", true);
        model.addAttribute("bookmark", bookmark);
        model.addAttribute("currentPage", bookmark.getNumber());
        model.addAttribute("count", count);

        return "mypage/myBookmark";
    }

}
