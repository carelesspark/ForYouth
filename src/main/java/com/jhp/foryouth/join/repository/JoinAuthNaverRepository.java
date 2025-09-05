package com.jhp.foryouth.join.repository;

import com.jhp.foryouth.user.entity.AuthNaver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JoinAuthNaverRepository extends JpaRepository<AuthNaver, Long> {
    Optional<AuthNaver> findByEmail(String email);
}