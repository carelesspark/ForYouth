package com.jhp.foryouth.board.entity;

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
public class Answer extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String adminId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_num", referencedColumnName = "num", nullable = false)
    private Question question;
}
