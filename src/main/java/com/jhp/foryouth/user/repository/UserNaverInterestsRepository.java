package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.domain.UserNaverInterests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserNaverInterestsRepository extends JpaRepository<UserNaverInterests, Long> {
    Optional<UserNaverInterests> findByNaver_Num(Long num);

    
    @Modifying
    @Transactional
    @Query("DELETE FROM UserNaverInterests uni WHERE uni.naver.num = :userNaverNum")
    void deleteByUserNaverNum(Long userNaverNum);
}
