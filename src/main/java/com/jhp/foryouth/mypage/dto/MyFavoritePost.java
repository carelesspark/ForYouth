package com.jhp.foryouth.mypage.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyFavoritePost {
    private Long postId;
    private String title;
    private String writerId;
    private LocalDateTime regDate;
    private Long likeCount;

    public MyFavoritePost(Long postId, String title, String writerId, LocalDateTime regDate, Long likeCount) {
        this.postId = postId;
        this.title = title;
        this.writerId = writerId;
        this.regDate = regDate;
        this.likeCount = likeCount;
    }
}
