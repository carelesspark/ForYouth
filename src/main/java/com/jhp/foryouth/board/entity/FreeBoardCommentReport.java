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
public class FreeBoardCommentReport extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String userId;

    private String provider;

    private String reportReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_num", referencedColumnName = "num")
    private FreeBoardComment freeBoardComment;
}
