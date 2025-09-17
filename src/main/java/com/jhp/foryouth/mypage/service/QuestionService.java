package com.jhp.foryouth.mypage.service;

import com.jhp.foryouth.mypage.dto.QuestionRequest;
import org.springframework.data.domain.Page;

public interface QuestionService {
    void saveQuestion(String writerId, String provider, String title, String content);

    Page<QuestionRequest> findQuestionsByUser(String writerId, String provider, int page);
}
