package com.jhp.foryouth.user.domain;

import com.jhp.foryouth.global.entity.Base;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserAuth extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String userId;

    private String userPw;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num", referencedColumnName = "num")
    private User user;

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }
}
