package com.jhp.foryouth.board.service.impl;

import com.jhp.foryouth.board.entity.PostType;
import com.jhp.foryouth.board.repository.*;
import com.jhp.foryouth.board.service.BoardTotalService;
import com.jhp.foryouth.mypage.dto.BookmarkRequest;
import com.jhp.foryouth.user.entity.UserBookmark;
import com.jhp.foryouth.user.repository.UserBookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class BoardTotalServiceImpl implements BoardTotalService {

    private final UserBookmarkRepository userBookmarkRepository;
    private final EducationPostRepository educationPostRepository;
    private final EmploymentSupportPostRepository employmentSupportPostRepository;
    private final FinancialSupportPostRepository financialSupportPostRepository;
    private final HousingSupportPostRepository housingSupportPostRepository;
    private final LocalNewsPostRepository localNewsPostRepository;
    private final WelfareBenefitPostRepository welfareBenefitPostRepository;


    @Override
    public Page<BookmarkRequest> getBookmarkByUser(String userId, String provider, String category, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "regDate"));

        String searchProvider = (provider == null) ? "normal" : provider;

        Page<UserBookmark> bookmarks = userBookmarkRepository.findBookmarkByConditions(userId, searchProvider, category, pageable);

        return bookmarks.map(this::convertToDto);
    }

    private BookmarkRequest convertToDto(UserBookmark userBookmark) {
        PostType postType = userBookmark.getPostType();
        Long postId = userBookmark.getPostNum();

        switch(postType) {
            case EDUCATION:
                return educationPostRepository.findById(postId)
                        .map(post -> BookmarkRequest.builder()
                                .bookmarkId(userBookmark.getNum())
                                .postId(post.getNum())
                                .postType(postType)
                                .category(postType.getDisplayName())
                                .title(post.getTitle())
                                .writerId(post.getWriterId())
                                .regDate(post.getRegDate())
                                .build())
                        .orElse(null);
            case EMPLOYMENT_SUPPORT:
                return employmentSupportPostRepository.findById(postId)
                        .map(post -> BookmarkRequest.builder()
                                .bookmarkId(userBookmark.getNum())
                                .postId(post.getNum())
                                .postType(postType)
                                .category(postType.getDisplayName())
                                .title(post.getTitle())
                                .writerId(post.getWriterId())
                                .regDate(post.getRegDate())
                                .build())
                        .orElse(null);
            case FINANCIAL_SUPPORT:
                return financialSupportPostRepository.findById(postId)
                        .map(post -> BookmarkRequest.builder()
                                .bookmarkId(userBookmark.getNum())
                                .postId(post.getNum())
                                .postType(postType)
                                .category(postType.getDisplayName())
                                .title(post.getTitle())
                                .writerId(post.getWriterId())
                                .regDate(post.getRegDate())
                                .build())
                        .orElse(null);
            case HOUSING_SUPPORT:
                return housingSupportPostRepository.findById(postId)
                        .map(post -> BookmarkRequest.builder()
                                .bookmarkId(userBookmark.getNum())
                                .postId(post.getNum())
                                .postType(postType)
                                .category(postType.getDisplayName())
                                .title(post.getTitle())
                                .writerId(post.getWriterId())
                                .regDate(post.getRegDate())
                                .build())
                        .orElse(null);
            case LOCAL_NEWS:
                return localNewsPostRepository.findById(postId)
                        .map(post -> BookmarkRequest.builder()
                                .bookmarkId(userBookmark.getNum())
                                .postId(post.getNum())
                                .postType(postType)
                                .category(postType.getDisplayName())
                                .title(post.getTitle())
                                .writerId(post.getWriterId())
                                .regDate(post.getRegDate())
                                .build())
                        .orElse(null);
            case WELFARE_BENEFIT:
                return welfareBenefitPostRepository.findById(postId)
                        .map(post -> BookmarkRequest.builder()
                                .bookmarkId(userBookmark.getNum())
                                .postId(post.getNum())
                                .postType(postType)
                                .category(postType.getDisplayName())
                                .title(post.getTitle())
                                .writerId(post.getWriterId())
                                .regDate(post.getRegDate())
                                .build())
                        .orElse(null);
            default:
                return null;
        }
    }




}
