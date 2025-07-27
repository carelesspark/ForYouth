package com.jhp.foryouth.mypage.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyFavoriteComment {
    private Long commentId;
    private String comment;
    private String postTitle;
    private String writerId;
    private LocalDateTime regDate;
    private Long likeCount;

    public MyFavoriteComment(Long commentId, String comment, String postTitle, String writerId, LocalDateTime regDate, Long likeCount) {
        this.commentId = commentId;
        this.comment = comment;
        this.postTitle = postTitle;
        this.writerId = writerId;
        this.regDate = regDate;
        this.likeCount = likeCount;
    }
}
