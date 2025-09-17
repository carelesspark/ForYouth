package com.jhp.foryouth.mypage.dto;

import com.jhp.foryouth.board.entity.QuestionStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestionRequest {
    private Long questionId;
    private String writerId;
    private String title;
    private String content;
    private QuestionStatus questionStatus;
    private LocalDateTime regDate;

    @Builder
    public QuestionRequest(Long questionId, String writerId, String title, String content, QuestionStatus questionStatus, LocalDateTime regDate) {
        this.questionId = questionId;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.questionStatus = questionStatus;
        this.regDate = regDate;
    }
}
