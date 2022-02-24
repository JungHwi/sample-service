package com.breeze.idus.user.service;

import com.breeze.core.auth.AuthToken;
import com.breeze.core.exception.BadRequestException;
import com.breeze.idus.user.service.dao.UserAuthDao;
import com.breeze.idus.user.service.dao.UserDao;
import com.breeze.idus.user.service.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserDao userDao;
    private final UserAuthDao userAuthDao;

    public AuthToken login(String email, String password) {
        User user = userDao.findByEmail(email);
        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("로그인 정보 오류.");
        }

        AuthToken authToken = new AuthToken(user.getId());
        userAuthDao.save(user.getId(), authToken.getRefreshToken());
        return authToken;
    }

    public void logout(long userId) {
        userAuthDao.delete(userId);
    }
}
