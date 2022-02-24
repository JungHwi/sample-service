package com.breeze.idus.user.persistence.repository.dsl;

import com.breeze.idus.user.persistence.entity.UserEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.breeze.idus.user.persistence.entity.QUserEntity.userEntity;

@Log4j2
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long searchUserCount(String name, String email) {
        return jpaQueryFactory
                .selectFrom(userEntity)
                .where(searchUserQuery(name, email))
                .fetchCount();
    }

    @Override
    public List<UserEntity> searchUserList(String name, String email, Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(userEntity)
                .where(searchUserQuery(name, email))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanBuilder searchUserQuery(String name, String email) {
        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasLength(name)) {
            builder.and(userEntity.name.contains(name));
        }

        if (StringUtils.hasLength(email)) {
            builder.and(userEntity.email.contains(email));
        }

        return builder;
    }
}
