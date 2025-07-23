package com.jhp.foryouth.board.entity;

import com.jhp.foryouth.global.entity.Base;
import com.jhp.foryouth.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FreeBoardComment extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String comment;

    private String writerId;

    private String provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_num", referencedColumnName = "num")
    private FreeBoard freeBoard;
}
