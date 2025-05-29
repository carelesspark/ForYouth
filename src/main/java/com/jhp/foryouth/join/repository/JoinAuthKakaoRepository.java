package com.jhp.foryouth.join.repository;

import com.jhp.foryouth.user.domain.AuthKakao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JoinAuthKakaoRepository extends JpaRepository<AuthKakao, Long> {
    Optional<AuthKakao> findByEmail(String email);
}