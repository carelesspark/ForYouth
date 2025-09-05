package com.jhp.foryouth.user.repository;

import com.jhp.foryouth.user.entity.AuthNaver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface NaverUserRepository extends JpaRepository<AuthNaver, Long> {
    Optional<AuthNaver> findByEmail(String email);

    @Modifying
    @Query("UPDATE AuthNaver n SET n.agreedEventAlarm = :agreedEventAlarm WHERE n.email = :email")
    void updateAgreedEventAlarmByNum(boolean agreedEventAlarm, String email);
}
