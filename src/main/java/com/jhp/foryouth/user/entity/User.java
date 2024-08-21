package com.jhp.foryouth.user.entity;

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

    private String addr;

    private String addr_detail;

    private String email;

    private String name;

    private String phone_num;

    private String post_num;
}
