package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.domain.UserKakaoInterests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserKakaoInterestsRepository extends JpaRepository<UserKakaoInterests, Long> {
    Optional<UserKakaoInterests> findByKakao_Num(Long num);


    @Modifying
    @Transactional
    @Query("DELETE FROM UserKakaoInterests uki WHERE uki.kakao.num = :userKakaoNum")
    void deleteByUserKakaoNum(Long userKakaoNum);
}
