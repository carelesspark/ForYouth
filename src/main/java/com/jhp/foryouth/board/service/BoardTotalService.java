package com.jhp.foryouth.board.service;

import com.jhp.foryouth.mypage.dto.BookmarkRequest;
import org.springframework.data.domain.Page;

public interface BoardTotalService {
    Page<BookmarkRequest> getBookmarkByUser(String userId, String provider, String category, int page);
}
