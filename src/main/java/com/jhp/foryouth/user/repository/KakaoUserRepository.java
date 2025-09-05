package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.entity.AuthKakao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface KakaoUserRepository extends JpaRepository<AuthKakao, Long> {
    Optional<AuthKakao> findByEmail(String email);

    @Modifying
    @Query("UPDATE AuthKakao k SET k.agreedEventAlarm = :agreedEventAlarm WHERE k.email = :email")
    void updateAgreedEventAlarmByNum(boolean agreedEventAlarm, String email);
}
