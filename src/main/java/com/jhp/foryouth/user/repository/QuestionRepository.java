package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.board.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query(value = "SELECT q FROM Question q LEFT JOIN FETCH q.answer WHERE q.writerId = :writerId AND q.writerProvider = :writerProvider",
            countQuery = "SELECT count(q) FROM Question q WHERE q.writerId = :writerId AND q.writerProvider = :writerProvider")
    Page<Question> findQuestionsWithAnswers(@Param("writerId") String writerId,
                                                   @Param("writerProvider") String writerProvider,
                                                   Pageable pageable);
}
