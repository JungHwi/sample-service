package com.breeze.idus.user.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "auth_token")
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenEntity {
    @Id
    private Long userId;

    @Column(nullable = false, length = 163)
    private String refreshToken;
}

/*
create table auth_token (
    user_id bigint not null primary key comment '회원 아이디',
    refresh_token char(163) not null comment 'JWT - refresh token'
) engine = 'memory' comment 'jwt refresh token 저장소';
*/