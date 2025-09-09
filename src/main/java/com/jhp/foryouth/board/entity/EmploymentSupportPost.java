package com.jhp.foryouth.board.entity;

import com.jhp.foryouth.global.entity.Base;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmploymentSupportPost extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String title;

    private String content;

    private String writerId;

    private String imgUrl;

    private String sourceUrl;

    private String region;

    private int visitCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_num", referencedColumnName = "num")
    private SupportCategory supportCategory;
}
