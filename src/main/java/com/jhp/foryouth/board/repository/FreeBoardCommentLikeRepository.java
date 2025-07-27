package com.jhp.foryouth.board.repository;

import com.jhp.foryouth.board.entity.FreeBoardCommentLike;
import com.jhp.foryouth.board.entity.FreeBoardPostLike;
import com.jhp.foryouth.mypage.dto.MyFavoriteComment;
import com.jhp.foryouth.mypage.dto.MyFavoritePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FreeBoardCommentLikeRepository extends JpaRepository<FreeBoardCommentLike, Long> {
    @Query("""
        SELECT new com.jhp.foryouth.mypage.dto.MyFavoriteComment(
            fb.num,
            fb.comment,
            b.title,
            fb.writerId,
            fb.regDate,
            (SELECT COUNT(l) FROM FreeBoardCommentLike l WHERE l.freeBoardComment.num = fb.num)
        )
        FROM FreeBoardCommentLike cl
        JOIN cl.freeBoardComment fb
        JOIN fb.freeBoard b
        WHERE cl.userId = :userId
            AND cl.provider = :provider
        ORDER BY fb.regDate DESC
    """)
    Page<MyFavoriteComment> findLikeCommentsByUser(@Param("userId") String userId,
                                                @Param("provider") String provider,
                                                Pageable pageable);

    @Query("""
        SELECT new com.jhp.foryouth.mypage.dto.MyFavoriteComment(
            fb.num,
            fb.comment,
            b.title,
            fb.writerId,
            fb.regDate,
            (SELECT COUNT(l) FROM FreeBoardCommentLike l WHERE l.freeBoardComment.num = fb.num)
        )
        FROM FreeBoardCommentLike cl
        JOIN cl.freeBoardComment fb
        JOIN fb.freeBoard b
        WHERE cl.userId = :userId
            AND cl.provider = :provider
            AND (:keyword IS NULL OR fb.comment LIKE %:keyword%)
            AND (:startDate IS NULL OR fb.regDate >= :startDate)
            AND (:endDate IS NULL OR fb.regDate <=: endDate)
        ORDER BY fb.regDate DESC
    """)
    Page<MyFavoriteComment> findLikeCommentsByFilter(@Param("userId") String userId,
                                                     @Param("provider") String provider,
                                                     @Param("keyword") String keyword,
                                                     @Param("startDate") LocalDateTime startDate,
                                                     @Param("endDate") LocalDateTime endDate,
                                                     Pageable pageable);
}
