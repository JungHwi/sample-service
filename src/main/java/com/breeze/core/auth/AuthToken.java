package com.breeze.core.auth;

import com.breeze.core.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.Calendar;
import java.util.Date;

public class AuthToken {
    @Getter
    private final String accessToken;
    @Getter
    private final String refreshToken;

    @JsonIgnore
    private final int ACCESS_DURATION_MINUTES = 5;
    @JsonIgnore
    private final int REFRESH_DURATION_DAYS = 30;

    public AuthToken(long userId) {
        Date accessExpireDate = DateUtils.addTime(Calendar.MINUTE, ACCESS_DURATION_MINUTES);
        Date refreshExpireDate = DateUtils.addTime(Calendar.DATE, REFRESH_DURATION_DAYS);

        this.accessToken = JWTUtils.generateToken(userId, accessExpireDate);
        this.refreshToken = JWTUtils.generateToken(userId, refreshExpireDate);
    }
}
