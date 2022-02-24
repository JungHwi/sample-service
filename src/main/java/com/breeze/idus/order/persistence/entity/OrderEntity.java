package com.breeze.idus.order.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "order_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @Column(nullable = false, updatable = false, length = 12)
    private String id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String productName;

    @CreatedDate
    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

}

/*
create table order_info (
    id varchar(12) primary key comment '주문 번호',
    user_id bigint not null comment '주문한 회원 아이디',
    product_name varchar(100) not null comment '상품명',
    created_at datetime not null comment '주문 일시'
) character set = 'utf8mb4' comment '주문 기본 정보';

create index idx_order_user_id on order_info (user_id);

insert into order_info (id, user_id, product_name, created_at)
values ('O00000000001', 1, '2인분 갈릭 버터 새우 밀키트', date_add(now(), interval -10 day)),
       ('O00000000002', 1, '곰돌이머핀캔들', date_add(now(), interval -7 day)),
       ('O00000000003', 1, '드로잉 원형 트레이', date_add(now(), interval -5 day)),
       ('O00000000004', 2, '매일 새로담아 아삭아삭! 생포기 김치 2KG', date_add(now(), interval -1 month)),
       ('O00000000005', 3, '자동차 스마트키 케이스 가죽 키홀더', now());
 */