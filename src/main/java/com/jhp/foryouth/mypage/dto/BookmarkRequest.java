package com.jhp.foryouth.mypage.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookmarkRequest {
    private Long postId;
    private String title;
    private String category;
    private LocalDateTime regDate;
    private Long visitCount;

    public BookmarkRequest(Long postId, String title, String category, LocalDateTime regDate, Long visitCount) {
        this.postId = postId;
        this.title = title;
        this.category = category;
        this.regDate = regDate;
        this.visitCount = visitCount;
    }
}
