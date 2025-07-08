package com.jhp.foryouth.mypage.repository;

import com.jhp.foryouth.user.domain.UserInterests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInterestsRepository extends JpaRepository<UserInterests, Long> {
    Optional<UserInterests> findByUserNum(Long num);
}
