package com.jhp.foryouth.board.entity;

import com.jhp.foryouth.global.entity.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrawledPost extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @Column(nullable = false, unique = true)
    private String url;

    private String source;

    private String summary;

    @Enumerated(EnumType.STRING)
    private CrawledPostStatus status;

    private String recommendedCategory;

    @PrePersist
    public void setDefaultStatus() {
        this.status = CrawledPostStatus.RAW;
    }
}
