package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.domain.AuthNaver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface NaverUserRepository extends JpaRepository<AuthNaver, Long> {
    Optional<AuthNaver> findByEmail(String email);
}
