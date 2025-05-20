package com.jhp.foryouth.user.domain;

import com.jhp.foryouth.global.entity.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class User extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String userPhone;

    private String userName;

    private String userBirth;

    private String userEmail;

    private Boolean agreedEventAlarm;
}
