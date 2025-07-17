package com.jhp.foryouth.mypage.repository;

import com.jhp.foryouth.user.domain.UserNaverInterests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserNaverInterestsRepository extends JpaRepository<UserNaverInterests, Long> {
    Optional<UserNaverInterests> findByNaver_Num(Long num);
}
