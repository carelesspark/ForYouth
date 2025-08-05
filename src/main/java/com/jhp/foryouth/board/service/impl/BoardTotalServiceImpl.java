package com.jhp.foryouth.board.service.impl;

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


@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class BoardTotalServiceImpl implements BoardTotalService {



    @Override
    public Page<BookmarkRequest> getBookmarkByUser(String email, String provider, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("regDate").descending());

        if(provider == null) {
            provider = "normal";

            if(category != null && !category.isEmpty()) {
                return
            }
        } else {

        }


    }
}
