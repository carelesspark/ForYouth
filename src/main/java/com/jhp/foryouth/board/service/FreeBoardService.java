package com.jhp.foryouth.board.service;

import com.jhp.foryouth.board.entity.FreeBoard;
import com.jhp.foryouth.board.entity.FreeBoardComment;
import com.jhp.foryouth.mypage.dto.MyComment;
import com.jhp.foryouth.mypage.dto.MyFavoriteComment;
import com.jhp.foryouth.mypage.dto.MyFavoritePost;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface FreeBoardService {
    Page<FreeBoard> getPostsByUser(String email, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page);

    Page<MyComment> getCommentsByUser(String email, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page);

    Page<MyFavoritePost> getPostsByUserLike(String email, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page);

    Page<MyFavoriteComment> getCommentsByUserLike(String email, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page);
}
