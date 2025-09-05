package com.jhp.foryouth.join.repository;

import com.jhp.foryouth.user.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckIdRepository extends JpaRepository<UserAuth, Long> {
    boolean existsByUserId(String userId);
}
