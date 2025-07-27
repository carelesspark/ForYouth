package com.jhp.foryouth.board.service.impl;

import com.jhp.foryouth.board.entity.FreeBoard;
import com.jhp.foryouth.board.entity.FreeBoardComment;
import com.jhp.foryouth.board.repository.FreeBoardCommentLikeRepository;
import com.jhp.foryouth.board.repository.FreeBoardCommentRepository;
import com.jhp.foryouth.board.repository.FreeBoardPostLikeRepository;
import com.jhp.foryouth.board.repository.FreeBoardRepository;
import com.jhp.foryouth.board.service.FreeBoardService;
import com.jhp.foryouth.mypage.dto.MyComment;
import com.jhp.foryouth.mypage.dto.MyFavoriteComment;
import com.jhp.foryouth.mypage.dto.MyFavoritePost;
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
public class FreeBoardServiceImpl implements FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;
    private final FreeBoardCommentRepository freeBoardCommentRepository;
    private final FreeBoardPostLikeRepository freeBoardPostLikeRepository;
    private final FreeBoardCommentLikeRepository freeBoardCommentLikeRepository;

    @Override
    public Page<FreeBoard> getPostsByUser(String writerId, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "regDate"));

        if(provider == null) {
            provider = "normal";

            if((keyword == null || keyword.isBlank()) && startDate == null && endDate == null) {
                return freeBoardRepository.findByWriterIdAndProvider(writerId, provider, pageable);
            }

            return freeBoardRepository.findByFilter(writerId, provider, keyword, startDate, endDate, pageable);
        } else {
            if((keyword == null || keyword.isBlank()) && startDate == null && endDate == null) {
                return freeBoardRepository.findByWriterIdAndProvider(writerId, provider, pageable);
            }

            return freeBoardRepository.findByFilter(writerId, provider, keyword, startDate, endDate, pageable);
        }
    }

    @Override
    public Page<MyComment> getCommentsByUser(String writerId, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "regDate"));

        if(provider == null) {
            provider = "normal";

            if((keyword == null || keyword.isBlank()) && startDate == null && endDate == null) {
                return freeBoardCommentRepository.findByWriterIdAndProvider(writerId, provider, pageable);
            }

            return freeBoardCommentRepository.findByFilter(writerId, provider, keyword, startDate, endDate, pageable);
        } else {
            if((keyword == null || keyword.isBlank()) && startDate == null && endDate == null) {
                return freeBoardCommentRepository.findByWriterIdAndProvider(writerId, provider, pageable);
            }

            return freeBoardCommentRepository.findByFilter(writerId, provider, keyword, startDate, endDate, pageable);
        }
    }

    @Override
    public Page<MyFavoritePost> getPostsByUserLike(String userId, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "regDate"));

        if(provider == null) {
            provider = "normal";

            if((keyword == null || keyword.isBlank()) && startDate == null && endDate == null) {
                return freeBoardPostLikeRepository.findLikePostsByUser(userId, provider, pageable);
            }

            return freeBoardPostLikeRepository.findLikePostByFilter(userId, provider, keyword, startDate, endDate, pageable);
        } else {
            if((keyword == null || keyword.isBlank()) && startDate == null && endDate == null) {
                return freeBoardPostLikeRepository.findLikePostsByUser(userId, provider, pageable);
            }

            return freeBoardPostLikeRepository.findLikePostByFilter(userId, provider, keyword, startDate, endDate, pageable);
        }
    }

    @Override
    public Page<MyFavoriteComment> getCommentsByUserLike(String userId, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "regDate"));

        if(provider == null) {
            provider = "normal";

            if((keyword == null || keyword.isBlank()) && startDate == null && endDate == null) {
                return freeBoardCommentLikeRepository.findLikeCommentsByUser(userId, provider, pageable);
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
