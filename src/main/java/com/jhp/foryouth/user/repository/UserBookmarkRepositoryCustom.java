package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.entity.UserBookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserBookmarkRepositoryCustom {
    Page<UserBookmark> findBookmarkByConditions(String userId, String provider, String category, Pageable pageable);
}
