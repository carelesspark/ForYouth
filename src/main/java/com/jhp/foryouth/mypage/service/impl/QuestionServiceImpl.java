package com.jhp.foryouth.mypage.service.impl;

import com.jhp.foryouth.board.entity.Question;
import com.jhp.foryouth.board.entity.QuestionStatus;
import com.jhp.foryouth.mypage.service.QuestionService;
import com.jhp.foryouth.user.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Override
    public void saveQuestion(String writerId, String provider, String title, String content) {
        String writerProvider = (provider == null) ? "normal" : provider;

        Question question = Question.builder()
                        .writerId(writerId)
                        .writerProvider(writerProvider)
                        .title(title)
                        .content(content)
                        .build();

        questionRepository.save(question);
    }
}
