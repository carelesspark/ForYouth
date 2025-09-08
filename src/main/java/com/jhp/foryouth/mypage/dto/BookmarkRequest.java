package com.jhp.foryouth.mypage.dto;

import com.jhp.foryouth.board.entity.PostType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookmarkRequest {
    private Long bookmarkId;
    private Long postId;
    private PostType postType;
    private String category;
    private String title;
    private String writerId;
    private LocalDateTime regDate;
    private Long visitCount;

    @Builder
    public BookmarkRequest(Long bookmarkId, Long postId, PostType postType, String category, String title, String writerId, LocalDateTime regDate, Long visitCount) {
        this.bookmarkId = bookmarkId;
        this.postId = postId;
        this.postType = postType;
        this.category = category;
        this.title = title;
        this.writerId = writerId;
        this.regDate = regDate;
        this.visitCount = visitCount;
    }
}
