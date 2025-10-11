package com.hansang.birthday.domain.auth.service;

import com.hansang.birthday.domain.auth.dto.response.LoginResponse;
import com.hansang.birthday.domain.auth.jwt.JwtTokenProvider;
import com.hansang.birthday.domain.auth.tokens.AuthTokens;
import com.hansang.birthday.domain.auth.tokens.AuthTokensGenerator;
import com.hansang.birthday.domain.auth.util.RefreshTokenExtractor;
import com.hansang.birthday.domain.user.domain.User;
import com.hansang.birthday.domain.user.repository.UserRepository;
import com.hansang.birthday.global.error.exception.BusinessException;
import com.hansang.birthday.global.error.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public LoginResponse login(HashMap<String, Object> userInfo) {
        long kakaoId = Long.parseLong(userInfo.get("kakaoId").toString());
        String name = userInfo.get("name").toString();
        String profileImageUrl = userInfo.get("profileImageUrl").toString();
        LocalDate birthday = (LocalDate) userInfo.get("birthday");

        User user = userRepository.findByKakaoId(kakaoId)
                .map(u -> {
                    u.updateUser(profileImageUrl,birthday); //기존 유저 => 프로필 갱신
                    return u;})
                .orElseGet(()-> {
                    return userRepository.save(User.of(kakaoId, name, profileImageUrl, birthday)); //신규 유저 => 저장
                });

        //토큰 발급 & refreshToken DB 업데이트
        AuthTokens token = authTokensGenerator.generate(user.getUserId().toString());
        user.updateRefreshToken(token.getRefreshToken());

        return new LoginResponse(user.getUserId(), token);
    }

    public void logout(HttpServletRequest request) {
        String refreshToken = RefreshTokenExtractor.fromCookie(request);
        if(refreshToken!=null) {
            userRepository.findByRefreshToken(refreshToken).ifPresent(user -> {
                user.deleteRefreshToken();
                userRepository.save(user);
            });
        }

    }
    public AuthTokens reissueAccessToken(HttpServletRequest request) {
        String refreshToken = RefreshTokenExtractor.fromCookie(request);
        if (refreshToken == null) {
            throw new BusinessException(ErrorCode.WRONG_REFRESH_TOKEN);
        }

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new BusinessException(ErrorCode.WRONG_REFRESH_TOKEN);
        }

        return userRepository.findByRefreshToken(refreshToken)
                .map(user -> authTokensGenerator.generateAccessOnly(user.getUserId().toString()))
                .orElseThrow(() -> new BusinessException(ErrorCode.WRONG_REFRESH_TOKEN));
    }

}
