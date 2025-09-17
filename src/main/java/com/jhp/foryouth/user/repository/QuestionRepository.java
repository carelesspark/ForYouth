package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.board.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByWriterIdAndWriterProviderOrderByRegDateDesc(String writerId, String writerProvider, Pageable pageable);
}
