package com.jhp.foryouth.user.domain;

import com.jhp.foryouth.global.entity.Base;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AuthNaver extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String email;

    private String name;

    private String birthday;

    private String birthyear;

    private String mobile;

    private String provider;

    @Builder.Default
    private Boolean agreedEventAlarm = false;
}
