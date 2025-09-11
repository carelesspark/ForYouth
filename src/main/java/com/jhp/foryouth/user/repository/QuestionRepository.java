package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.board.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
