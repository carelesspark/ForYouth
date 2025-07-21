package com.jhp.foryouth.board.service.impl;

import com.jhp.foryouth.board.entity.FreeBoard;
import com.jhp.foryouth.board.repository.FreeBoardRepository;
import com.jhp.foryouth.board.service.FreeBoardService;
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

    @Override
    public Page<FreeBoard> getPostsByUser(String email, String provider, String keyword, LocalDateTime startDate, LocalDateTime endDate, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("regDate").descending());

        String writerId = email;

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
}
