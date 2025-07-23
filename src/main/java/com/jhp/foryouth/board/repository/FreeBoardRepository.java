package com.jhp.foryouth.board.repository;

import com.jhp.foryouth.board.entity.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FreeBoardRepository  extends JpaRepository<FreeBoard, Long> {
    Page<FreeBoard> findByWriterIdAndProvider(String writerId, String provider, Pageable pageable);

    @Query("SELECT f FROM FreeBoard f " +
    "WHERE f.writerId = :writerId AND f.provider = :provider " +
    "AND (:keyword IS NULL OR f.title LIKE %:keyword% OR f.content LIKE %:keyword%) " +
    "AND (:startDate IS NULL OR f.regDate >= :startDate) " +
    "AND (:endDate IS NULL OR f.regDate <= :endDate)")
    Page<FreeBoard> findByFilter(@Param("writerId") String writerId,
                                 @Param("provider") String provider,
                                 @Param("keyword") String keyword,
                                 @Param("startDate") LocalDateTime startDate,
                                 @Param("endDate") LocalDateTime endDate,
                                 Pageable pageable);
}
