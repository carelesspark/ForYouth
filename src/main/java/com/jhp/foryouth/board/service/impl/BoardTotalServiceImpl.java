package com.jhp.foryouth.board.service.impl;

import com.jhp.foryouth.board.repository.BoardCategoryRepository;
import com.jhp.foryouth.board.service.BoardTotalService;
import com.jhp.foryouth.mypage.dto.BookmarkRequest;
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

    private final BoardCategoryRepository boardCategoryRepository;

    @Override
    public Page<BookmarkRequest> getBookmarkByUser(String email, String provider, String keyword, String category, LocalDateTime startDate, LocalDateTime endDate, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "regDate"));

        if(provider == null) {
            provider = "normal";

            if((keyword == null || keyword.isBlank()) && startDate == null && endDate == null && (category == null || category.isBlank())) {
                return boardCategoryRepository.findByEmailAndCategory();
            }

            return freeBoardCommentLikeRepository.findLikeCommentsByFilter(userId, provider, keyword, startDate, endDate, pageable);
        } else {
            if((keyword == null || keyword.isBlank()) && startDate == null && endDate == null) {
                return freeBoardCommentLikeRepository.findLikeCommentsByUser(userId, provider, pageable);
            }

            return freeBoardCommentLikeRepository.findLikeCommentsByFilter(userId, provider, keyword, startDate, endDate, pageable);
        }
    }
}
