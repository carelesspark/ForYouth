package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.domain.UserAuth;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByUserId(String userId);

    Optional<UserAuth> findByUserNum(Long userNum);

    @Query("SELECT ua FROM UserAuth ua JOIN FETCH ua.user WHERE ua.userId = :userId")
    Optional<UserAuth> findByUserIdWithUser(@Param("username") String userId);
    // N + 1 문제 방지
}
