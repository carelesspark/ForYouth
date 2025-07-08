package com.jhp.foryouth.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserNaverInterests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String interests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_naver_num", referencedColumnName = "num")
    private AuthNaver naver;
}
