package com.breeze.core.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JWTUtils {

    private static final String KEY = "jwtSampleKey";

    public static String generateToken(long userId, Date expireDate) {
        return JWT.create()
                .withClaim("exp", expireDate)
                .withClaim("userId", userId)
                .sign(Algorithm.HMAC512(KEY));
    }
}
