package com.jhp.foryouth.join.repository;

import com.jhp.foryouth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinUserRepository extends JpaRepository<User, Long> {
}