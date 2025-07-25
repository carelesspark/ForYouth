package com.jhp.foryouth.board.repository;

import com.jhp.foryouth.board.entity.FreeBoardComment;
import com.jhp.foryouth.board.entity.FreeBoardPostLike;
import com.jhp.foryouth.mypage.dto.MyComment;
import com.jhp.foryouth.mypage.dto.MyFavoritePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FreeBoardPostLikeRepository extends JpaRepository<FreeBoardPostLike, Long> {
    @Query("""
        SELECT new com.jhp.foryouth.mypage.dto.MyFavoritePost(
            f.num,
            f.title,
            f.writerId,
            f.regDate,
            (SELECT COUNT(l) FROM FreeBoardPostLike l WHERE l.freeBoard.num = f.num)
        )
        FROM FreeBoardPostLike pl
        JOIN pl.freeBoard f
        WHERE pl.userId = :userId
            AND pl.provider = :provider
        ORDER BY f.regDate DESC
    """)
    Page<MyFavoritePost> findLikePostsByUser(@Param("userId") String userId,
                                             @Param("provider") String provider,
                                             Pageable pageable);

    @Query("""
        SELECT new com.jhp.foryouth.mypage.dto.MyFavoritePost(
            f.num,
            f.title,
            f.writerId,
            f.regDate,
            (SELECT COUNT(l) FROM FreeBoardPostLike l WHERE l.freeBoard.num = f.num)
        )
        FROM FreeBoardPostLike pl
        JOIN pl.freeBoard f
        WHERE pl.userId = :userId
            AND pl.provider = :provider
            AND (:keyword IS NULL OR f.title LIKE %:keyword% OR f.content LIKE %:keyword%)
            AND (:startDate IS NULL OR pl.regDate >= :startDate)
            AND (:endDate IS NULL OR pl.regDate <=: endDate)
        ORDER BY f.regDate DESC
    """)
    Page<MyFavoritePost> findLikePostByFilter(@Param("userId") String userId,
                                              @Param("provider") String provider,
                                              @Param("keyword") String keyword,
                                              @Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate,
                                              Pageable pageable);
}
