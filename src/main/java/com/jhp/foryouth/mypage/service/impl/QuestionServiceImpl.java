package com.jhp.foryouth.mypage.service.impl;

import com.jhp.foryouth.board.entity.Question;
import com.jhp.foryouth.mypage.dto.QuestionRequest;
import com.jhp.foryouth.mypage.service.QuestionService;
import com.jhp.foryouth.user.repository.QuestionRepository;
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

    @Override
    public Page<QuestionRequest> findQuestionsByUser(String writerId, String provider, int page) {
        String writerProvider = (provider == null) ? "normal" : provider;

        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "regDate"));

        Page<Question> questionPage = questionRepository.findByWriterIdAndWriterProviderOrderByRegDateDesc(writerId, writerProvider, pageable);

        return questionPage.map(question ->
                QuestionRequest.builder()
                        .questionId(question.getNum())
                        .writerId(question.getWriterId())
                        .title(question.getTitle())
                        .content(question.getContent())
                        .questionStatus(question.getQuestionStatus())
                        .regDate(question.getRegDate())
                        .build());
    }
}
