package com.hansang.birthday.domain.auth.jwt;

import com.hansang.birthday.global.error.exception.BusinessException;
import com.hansang.birthday.global.error.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {
    private final Key key;
    public JwtTokenProvider(@Value("${JWT_SECRET_KEY}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    //=============유저용=============
    public String accessTokenGenerate(String subject, Date expiredAt) {
        return Jwts.builder()
                .setSubject(subject) //id
                .claim("role", "USER")
                .setExpiration(expiredAt)
                .signWith(this.key, SignatureAlgorithm.HS256)
                .compact();

    }

    public String refreshTokenGenerate(Date expiredAt) {
        return Jwts.builder()
                .setExpiration(expiredAt)
                .signWith(this.key, SignatureAlgorithm.HS256)
                .compact();

    }
    //=============게스트용=============
    public String generateGuestToken(String guestId, String nickname, Long birthdayId, Date expiredAt) {
        return Jwts.builder()
                .setSubject(guestId) // JWT 표준 claim
                .claim("nickname", nickname)
                .claim("birthdayId", birthdayId)
                .claim("role", "GUEST")
                .setExpiration(expiredAt)
                .signWith(this.key, SignatureAlgorithm.HS256)
                .compact();

    }

    // ============= 공통 유틸 =============
    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(this.key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch(SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
               IllegalArgumentException e){
            throw new BusinessException(ErrorCode.WRONG_REFRESH_TOKEN);
        }
    }

}
