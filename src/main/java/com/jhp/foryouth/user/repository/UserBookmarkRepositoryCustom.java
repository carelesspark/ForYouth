package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.entity.UserBookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface UserBookmarkRepositoryCustom {
    Page<UserBookmark> findBookmarkByConditions(String userId, String provider, String keyword, String category, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
