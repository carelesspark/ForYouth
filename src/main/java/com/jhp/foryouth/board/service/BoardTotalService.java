package com.jhp.foryouth.board.service;

import com.jhp.foryouth.mypage.dto.BookmarkRequest;
import com.jhp.foryouth.mypage.dto.MyFavoriteComment;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface BoardTotalService {
    Page<BookmarkRequest> getBookmarkByUser(String email, String provider, String keyword, String category, LocalDateTime startDate, LocalDateTime endDate, int page);
}
