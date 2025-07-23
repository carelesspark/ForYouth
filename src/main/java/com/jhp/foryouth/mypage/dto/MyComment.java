package com.jhp.foryouth.mypage.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyComment {
    private Long commentId;
    private String comment;
    private LocalDateTime regDate;
    private String postTitle;
    private Long likeCount;

    public MyComment(Long commentId, String comment, LocalDateTime regDate, String postTitle, Long likeCount) {
        this.commentId = commentId;
        this.comment = comment;
        this.regDate = regDate;
        this.postTitle = postTitle;
        this.likeCount = likeCount;
    }
}
