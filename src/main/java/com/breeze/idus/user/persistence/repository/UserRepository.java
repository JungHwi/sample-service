package com.breeze.idus.user.persistence.repository;

import com.breeze.core.config.jpa.DefaultJpaRepository;
import com.breeze.idus.user.persistence.entity.UserEntity;
import com.breeze.idus.user.persistence.repository.dsl.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends DefaultJpaRepository<UserEntity, Long>, UserRepositoryCustom {

    Optional<UserEntity> findByEmail(String email);

}
