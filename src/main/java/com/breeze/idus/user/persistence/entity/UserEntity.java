package com.breeze.idus.user.persistence.entity;

import com.breeze.core.code.Gender;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, length = 20)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 1)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(mappedBy = "user")
    private LastActionEntity lastAction;

}

/*
create table user (
    id bigint primary key auto_increment comment '회원 아이디',
    name varchar(20) not null comment '회원명',
    nickname varchar(30) not null comment '별명. 닉네임',
    password char(60) not null comment '비밀번호(bcrypt)',
    phone_number varchar(20) not null comment '전화번호',
    email varchar(100) not null comment '이메일',
    gender char(1) comment '성별'
) character set = 'utf8mb4' comment '회원 기본 정보';

create index idx_user_email on `user` (email);
create index idx_user_name on `user` (name);

INSERT INTO user (name, nickname, password, phone_number, email, gender) VALUES
('김정휘', 'breeze', 'Breeze123!@#', '01045739365', 'chonghwi0@gmail.com', 'M'),
('Tester', 'tester', '1234567890aA@', '01012345678', 'tester@gmail.com', 'F'),
('감자', 'potato', 'potato1!POTA', '01023456789', 'potato@gmail.com', 'F');

*/

