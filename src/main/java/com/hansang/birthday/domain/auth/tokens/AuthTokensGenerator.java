package com.hansang.birthday.domain.auth.tokens;


import com.hansang.birthday.domain.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthTokensGenerator {
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 14; //14일 => 추후 수정 필요
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일
    private static final long GUEST_TOKEN_EXPIRE_TIME = 1000 * 60 * 60;  // 1일

    private final JwtTokenProvider jwtTokenProvider;

    //=============유저용=============
    public AuthTokens generate(String userId) {
        long now = new Date().getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String accessToken = jwtTokenProvider.accessTokenGenerate(userId, accessTokenExpiredAt);
        String refreshToken = jwtTokenProvider.refreshTokenGenerate(refreshTokenExpiredAt);

        return AuthTokens.create(accessToken,refreshToken,BEARER_TYPE,ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }

    public AuthTokens generateAccessOnly(String userId) {
        long now = new Date().getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = jwtTokenProvider.accessTokenGenerate(userId, accessTokenExpiredAt);
        return AuthTokens.create(accessToken,null,BEARER_TYPE,ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }
    //=============게스트용=============
    public AuthTokens generateGuestToken(String nickname, Long birthdayId) {
        String guestId = UUID.randomUUID().toString();
        long now = new Date().getTime();
        Date accessTokenExpiredAt = new Date(now + GUEST_TOKEN_EXPIRE_TIME);
        String guestToken = jwtTokenProvider.generateGuestToken(guestId,nickname,birthdayId, accessTokenExpiredAt);
        return AuthTokens.create(guestToken,null,BEARER_TYPE,GUEST_TOKEN_EXPIRE_TIME / 1000L);
    }
}
