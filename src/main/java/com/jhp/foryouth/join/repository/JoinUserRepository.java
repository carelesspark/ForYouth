package com.jhp.foryouth.join.repository;

import com.jhp.foryouth.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinUserRepository extends JpaRepository<User, Long> {
}