package com.jhp.foryouth.mypage.controller;

import com.jhp.foryouth.board.service.BoardTotalService;
import com.jhp.foryouth.login.config.CustomUserDetails;
import com.jhp.foryouth.mypage.dto.BookmarkRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetBookmarkController {

    private final BoardTotalService boardTotalService;

    @PostMapping("/api/user/bookmark")
    public ResponseEntity<Page<BookmarkRequest>> getAllBookmark(@RequestParam(required = false) String category, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        String email = null;
        String provider = null;

        if(principal instanceof CustomUserDetails customUserDetails) {
            email = customUserDetails.getEmail();
        } else if(authentication instanceof OAuth2AuthenticationToken authenticationToken) {
            OAuth2User oauth2User = (OAuth2User) principal;
            email = (String) oauth2User.getAttributes().get("email");
            provider = authenticationToken.getAuthorizedClientRegistrationId();
        }

        Page<BookmarkRequest> bookmark = boardTotalService.getBookmarkByUser(email, provider, category, page, size);

        return ResponseEntity.ok(bookmark);
    }
}
