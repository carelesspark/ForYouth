package com.jhp.foryouth.user.entity;

import com.jhp.foryouth.board.entity.PostType;
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
public class UserBookmark extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String userId;

    private String provider;

    @Column(nullable = false)
    private Long postNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostType postType;
}
