package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByUserId(String userId);
}
