package com.jhp.foryouth.mypage.repository;

import com.jhp.foryouth.user.domain.UserKakaoInterests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserKakaoInterestsRepository extends JpaRepository<UserKakaoInterests, Long> {
    Optional<UserKakaoInterests> findByKakao_Num(Long num);
}
