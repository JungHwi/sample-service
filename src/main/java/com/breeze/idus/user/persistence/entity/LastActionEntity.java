package com.breeze.idus.user.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "last_action")
@AllArgsConstructor
@NoArgsConstructor
public class LastActionEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime loginAt;

    @Column(length = 12)
    private String orderId;

    @OneToOne()
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

}

/*
create table last_action (
    user_id bigint primary key not null comment '회원 아이디',
    login_at datetime not null comment '마지막 로그인 일시',
    order_id varchar(12) comment '마지막 주문 아이디'
) character set = 'utf8mb4' comment '회원의 마지막 액션 정보';

insert into last_action (user_id, login_at, order_id)
values(1, now(), 'O00000000003'),
      (2, now(), 'O00000000004'),
      (3, now(), 'O00000000005');
 */