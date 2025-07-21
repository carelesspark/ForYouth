package com.jhp.foryouth.board.service;

import com.jhp.foryouth.board.entity.FreeBoard;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface FreeBoardService {
    Page<FreeBoard> getPostsByUser(String email, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page);
}
