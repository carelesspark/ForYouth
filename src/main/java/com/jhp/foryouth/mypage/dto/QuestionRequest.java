package com.jhp.foryouth.mypage.dto;

import com.jhp.foryouth.board.entity.Question;
import com.jhp.foryouth.board.entity.QuestionStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionRequest {
    private final Long questionId;
    private final String writerId;
    private final String title;
    private final String content;
    private final QuestionStatus questionStatus;
    private final LocalDateTime regDate;
    private final String answerContent;

    public QuestionRequest(Question question) {
        this.questionId = question.getNum();
        this.writerId = question.getWriterId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.questionStatus = question.getQuestionStatus();
        this.regDate = question.getRegDate();
        this.answerContent = (question.getAnswer() != null) ? question.getAnswer().getContent() : null;
    }
}
