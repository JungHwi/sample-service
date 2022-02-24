package com.breeze.idus.user.service.dao;

import com.breeze.idus.user.persistence.entity.AuthTokenEntity;
import com.breeze.idus.user.persistence.repository.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserAuthDao {

    private final AuthTokenRepository authTokenRepository;

    @Transactional
    public void save(long userId, String refreshToken) {
        AuthTokenEntity entity = new AuthTokenEntity(userId, refreshToken);
        authTokenRepository.save(entity);
    }

    @Transactional
    public void delete(long userId) {
        authTokenRepository.deleteById(userId);
    }
}
