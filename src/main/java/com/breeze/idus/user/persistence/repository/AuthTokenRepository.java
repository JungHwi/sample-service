package com.breeze.idus.user.persistence.repository;

import com.breeze.core.config.jpa.DefaultJpaRepository;
import com.breeze.idus.user.persistence.entity.AuthTokenEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends DefaultJpaRepository<AuthTokenEntity, Long> {
}
