package com.jhp.foryouth.user.repository.impl;

import com.jhp.foryouth.board.entity.PostType;
import com.jhp.foryouth.user.entity.UserBookmark;
import com.jhp.foryouth.user.repository.UserBookmarkRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.jhp.foryouth.user.entity.QUserBookmark.userBookmark;

@RequiredArgsConstructor
public class UserBookmarkRepositoryImpl implements UserBookmarkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserBookmark> findBookmarkByConditions(String userId, String provider, String category, Pageable pageable) {
        List<UserBookmark> content = queryFactory
                .selectFrom(userBookmark)
                .where(userIdEq(userId),
                        providerEq(provider),
                        postTypeEq(category))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(userBookmark.regDate.desc())
                .fetch();

        long total = queryFactory
                .select(userBookmark.count())
                .from(userBookmark)
                .where(
                        userIdEq(userId),
                        providerEq(provider),
                        postTypeEq(category)
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression userIdEq(String userId) {
        return StringUtils.hasText(userId) ? userBookmark.userId.eq(userId) : null;
    }

    private BooleanExpression providerEq(String provider) {
        return StringUtils.hasText(provider) ? userBookmark.provider.eq(provider) : null;
    }

    private BooleanExpression postTypeEq(String category) {
        if(!StringUtils.hasText(category)) {
            return null;
        }
        try {
            PostType postType = PostType.valueOf(category.toUpperCase());
            return userBookmark.postType.eq(postType);
        } catch(IllegalArgumentException e) {
            return null;
        }
    }
}
