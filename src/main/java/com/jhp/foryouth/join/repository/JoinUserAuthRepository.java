package com.jhp.foryouth.join.repository;

import com.jhp.foryouth.user.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinUserAuthRepository extends JpaRepository<UserAuth, Long> {
}