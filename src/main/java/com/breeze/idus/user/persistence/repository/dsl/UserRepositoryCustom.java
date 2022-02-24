package com.breeze.idus.user.persistence.repository.dsl;

import com.breeze.idus.user.persistence.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepositoryCustom {

    long searchUserCount(String name, String email);
    List<UserEntity> searchUserList(String name, String email, Pageable pageable);
}
