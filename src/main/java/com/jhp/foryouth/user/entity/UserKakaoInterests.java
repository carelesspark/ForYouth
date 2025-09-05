package com.jhp.foryouth.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserKakaoInterests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String interests;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_kakao_num", referencedColumnName = "num")
    private AuthKakao kakao;
}
