package com.jhp.foryouth.mypage.controller;

import com.jhp.foryouth.board.entity.FreeBoard;
import com.jhp.foryouth.board.entity.FreeBoardComment;
import com.jhp.foryouth.board.service.BoardTotalService;
import com.jhp.foryouth.board.service.FreeBoardService;
import com.jhp.foryouth.login.config.CustomUserDetails;
import com.jhp.foryouth.mypage.dto.BookmarkRequest;
import com.jhp.foryouth.mypage.dto.MyComment;
import com.jhp.foryouth.mypage.dto.MyFavoriteComment;
import com.jhp.foryouth.mypage.dto.MyFavoritePost;
import com.jhp.foryouth.mypage.service.InterestsService;
import com.jhp.foryouth.mypage.service.QuestionService;
import com.jhp.foryouth.mypage.service.UserInfoService;
import com.jhp.foryouth.user.dto.KakaoDTO;
import com.jhp.foryouth.user.dto.NaverDTO;
import com.jhp.foryouth.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequestMapping("/mypage")
@Controller
@Log4j2
@RequiredArgsConstructor
public class MypageController {

    private final UserInfoService userInfoService;
    private final InterestsService interestsService;
    private final FreeBoardService freeBoardService;
    private final BoardTotalService boardTotalService;
    private final QuestionService questionService;

    @GetMapping("/user")
    public String mypageMain(Model model) {
        log.info("유저 마이페이지 메인");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            return "login/loginMain";
        }

        model.addAttribute("pageTitle", "ForYouth 마이페이지");
        model.addAttribute("cssPath", "/css/mypage/mypageMain.css");
        model.addAttribute("jsPath", "/js/mypage/marketingChange.js");
        model.addAttribute("jsPath2", "/js/mypage/saveInterests.js");
        model.addAttribute("jsPath3", "/js/mypage/interestsCheckbox.js");
        model.addAttribute("jsPath5", "/js/mypage/withdrawUser.js");

        Object principal = authentication.getPrincipal();
        String email = null;
        String provider = null;
        String userId = authentication.getName();

        if(principal instanceof CustomUserDetails customUser) {
            email = customUser.getEmail();
        } else if(authentication instanceof OAuth2AuthenticationToken oAuthToken) {
            OAuth2User oAuth2User = (OAuth2User) principal;
            email = (String) oAuth2User.getAttributes().get("email");
            provider = oAuthToken.getAuthorizedClientRegistrationId();
        }

        if(provider == null) {
            model.addAttribute("jsPath4", "/js/mypage/changePw.js");
            UserDTO dto = userInfoService.normalUser(userId);
            model.addAttribute("dto", dto);
            model.addAttribute("userId", userId);
        } else if(provider.equals("naver")) {
            NaverDTO dto = userInfoService.authNaverUser(email);
            model.addAttribute("dto", dto);
            model.addAttribute("provider", provider);
        } else if(provider.equals("kakao")) {
            KakaoDTO dto = userInfoService.authKakaoUser(email);
            model.addAttribute("dto", dto);
            model.addAttribute("provider", provider);
        }

        String interests = interestsService.getUserInterests(userId, provider, email);
        model.addAttribute("interests", interests);

        model.addAttribute("isLogin", true);
        model.addAttribute("email", email);

        return "mypage/mypageMain";
    }

    @GetMapping("/post")
    public String myPost(@RequestParam(defaultValue = "1") int page,
                         @RequestParam(required = false) String period,
                         @RequestParam(required = false) String keyword,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                         Model model) {
        log.info("내가 작성한 게시글 페이지");

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

        LocalDateTime start = (startDate != null) ? startDate.atStartOfDay() : null;
        LocalDateTime end = (startDate != null) ? endDate.atTime(LocalTime.MAX) : null;

        int zeroBasedPage = Math.max(page - 1, 0);

        Page<FreeBoard> postPage = freeBoardService.getPostsByUser(email, provider, keyword, start, end, zeroBasedPage);
        long count = postPage.getTotalElements();

        model.addAttribute("pageTitle", "ForYouth 마이페이지");
        model.addAttribute("cssPath", "/css/mypage/myPost.css");
        model.addAttribute("jsPath", "/js/mypage/postDate.js");

        model.addAttribute("isLogin", true);

        model.addAttribute("postPage", postPage);
        model.addAttribute("currentPage", postPage.getNumber());
        model.addAttribute("keyword", keyword);
        model.addAttribute("period", period);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("count", count);

        return "mypage/myPost";
    }

    @GetMapping("/comment")
    public String myComment(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(required = false) String period,
                            @RequestParam(required = false) String keyword,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                            Model model) {


        log.info("내가 작성한 댓글 페이지");

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

        LocalDateTime start = (startDate != null) ? startDate.atStartOfDay() : null;
        LocalDateTime end = (startDate != null) ? endDate.atTime(LocalTime.MAX) : null;

        int zeroBasedPage = Math.max(page - 1, 0);

        Page<MyComment> commentPage = freeBoardService.getCommentsByUser(email, provider, keyword, start, end, zeroBasedPage);
        long count = commentPage.getTotalElements();

        model.addAttribute("pageTitle", "ForYouth 마이페이지");
        model.addAttribute("cssPath", "/css/mypage/myComment.css");
        model.addAttribute("jsPath", "/js/mypage/postDate.js");

        model.addAttribute("isLogin", true);
        model.addAttribute("commentPage", commentPage);
        model.addAttribute("currentPage", commentPage.getNumber());
        model.addAttribute("keyword", keyword);
        model.addAttribute("period", period);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("count", count);

        return "mypage/myComment";
    }

    @GetMapping("/like-post")
    public String myFavoritePost(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(required = false) String period,
                                 @RequestParam(required = false) String keyword,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                 Model model) {

        
        log.info("내가 좋아요 누른 게시글 페이지");

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

        LocalDateTime start = (startDate != null) ? startDate.atStartOfDay() : null;
        LocalDateTime end = (startDate != null) ? endDate.atTime(LocalTime.MAX) : null;

        int zeroBasedPage = Math.max(page - 1, 0);

        Page<MyFavoritePost> likePostPage = freeBoardService.getPostsByUserLike(email, provider, keyword, start, end, zeroBasedPage);
        long count = likePostPage.getTotalElements();

        model.addAttribute("pageTitle", "ForYouth 마이페이지");
        model.addAttribute("cssPath", "/css/mypage/myFavoritePost.css");
        model.addAttribute("jsPath", "/js/mypage/postDate.js");

        model.addAttribute("isLogin", true);
        model.addAttribute("likePostPage", likePostPage);
        model.addAttribute("currentPage", likePostPage.getNumber());
        model.addAttribute("keyword", keyword);
        model.addAttribute("period", period);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("count", count);


        return "mypage/myFavoritePost";
    }

    @GetMapping("/like-comment")
    public String myFavoriteComment(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(required = false) String period,
                                    @RequestParam(required = false) String keyword,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                    Model model) {
        
        log.info("내가 좋아요 누른 댓글 페이지");

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

        LocalDateTime start = (startDate != null) ? startDate.atStartOfDay() : null;
        LocalDateTime end = (startDate != null) ? endDate.atTime(LocalTime.MAX) : null;

        int zeroBasedPage = Math.max(page - 1, 0);

        Page<MyFavoriteComment> likeCommentPage = freeBoardService.getCommentsByUserLike(email, provider, keyword, start, end, zeroBasedPage);
        long count = likeCommentPage.getTotalElements();

        model.addAttribute("pageTitle", "ForYouth 마이페이지");
        model.addAttribute("cssPath", "/css/mypage/myFavoriteComment.css");
        model.addAttribute("jsPath", "/js/mypage/postDate.js");

        model.addAttribute("isLogin", true);
        model.addAttribute("likeCommentPage", likeCommentPage);
        model.addAttribute("currentPage", likeCommentPage.getNumber());
        model.addAttribute("keyword", keyword);
        model.addAttribute("period", period);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("count", count);

        return "mypage/myFavoriteComment";
    }

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

        return "redirect:/mypage/myPageQnA";
    }
}
