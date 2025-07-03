package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.domain.AuthKakao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface KakaoUserRepository extends JpaRepository<AuthKakao, Long> {
    Optional<AuthKakao> findByEmail(String email);
}
