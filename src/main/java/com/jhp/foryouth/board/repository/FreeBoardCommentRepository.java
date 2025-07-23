package com.jhp.foryouth.board.repository;

import com.jhp.foryouth.board.entity.FreeBoard;
import com.jhp.foryouth.board.entity.FreeBoardComment;
import com.jhp.foryouth.mypage.dto.MyComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FreeBoardCommentRepository extends JpaRepository<FreeBoardComment, Long> {
    @Query("""
          SELECT new com.jhp.foryouth.mypage.dto.MyComment(
            c.num, c.comment, c.regDate, f.title,
                (SELECT COUNT(cl) FROM FreeBoardCommentLike cl WHERE cl.freeBoardComment.num = c.num)
          )
          FROM FreeBoardComment c
          JOIN c.freeBoard f
          WHERE c.writerId = :writerId
            AND c.provider = :provider
          ORDER BY c.regDate DESC
    """)
    Page<MyComment> findByWriterIdAndProvider(@Param("writerId") String writerId,
                                 @Param("provider") String provider,
                                 Pageable pageable);

    @Query("""
          SELECT new com.jhp.foryouth.mypage.dto.MyComment(
            c.num, c.comment, c.regDate, f.title,
                (SELECT COUNT(cl) FROM FreeBoardCommentLike cl WHERE cl.freeBoardComment.num = c.num)
          )
          FROM FreeBoardComment c
          JOIN c.freeBoard f
          WHERE c.writerId = :writerId
            AND c.provider = :provider
            AND (:keyword IS NULL OR c.comment LIKE %:keyword%)
            AND (:startDate IS NULL OR c.regDate >= :startDate)
            AND (:endDate IS NULL OR c.regDate <=: endDate)
          ORDER BY c.regDate DESC
    """)
    Page<MyComment> findByFilter(@Param("writerId") String writerId,
                                 @Param("provider") String provider,
                                 @Param("keyword") String keyword,
                                 @Param("startDate") LocalDateTime startDate,
                                 @Param("endDate") LocalDateTime endDate,
                                 Pageable pageable);
}
