package com.jhp.foryouth.mypage.repository;

import com.jhp.foryouth.user.domain.UserInterests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserInterestsRepository extends JpaRepository<UserInterests, Long> {
    Optional<UserInterests> findByUserNum(Long num);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserInterests ui WHERE ui.user.num = :userNum")
    void deleteByUserNum(Long userNum);
}
