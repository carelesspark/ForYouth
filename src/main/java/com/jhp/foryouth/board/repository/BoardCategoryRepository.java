package com.jhp.foryouth.board.repository;

import com.jhp.foryouth.board.entity.FreeBoard;
import com.jhp.foryouth.board.entity.SupportCategory;
import com.jhp.foryouth.mypage.dto.BookmarkRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface BoardCategoryRepository extends JpaRepository<SupportCategory, Long> {
    Page<BookmarkRequest> findByEmailAndCategory(String email, String category, Pageable pageable);
}
