package com.jhp.foryouth.user.repository;


import com.jhp.foryouth.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNameAndUserEmail(String userName, String userEmail);

    @Modifying
    @Query("UPDATE User u SET u.agreedEventAlarm = :agreedEventAlarm WHERE u.num = :num")
    void updateAgreedEventAlarmByNum(boolean agreedEventAlarm, Long num);
}
