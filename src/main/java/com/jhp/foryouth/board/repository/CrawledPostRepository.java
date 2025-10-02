package com.jhp.foryouth.board.repository;

import com.jhp.foryouth.board.entity.CrawledPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrawledPostRepository extends JpaRepository<CrawledPost, Long> {

    boolean existsByUrl(String url);
}
