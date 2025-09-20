package com.hansang.birthday.domain.auth.controller;

import com.hansang.birthday.domain.auth.dto.request.KakaoLoginRequest;
import com.hansang.birthday.domain.auth.service.AuthService;
import com.hansang.birthday.domain.auth.tokens.AuthTokens;
import com.hansang.birthday.domain.auth.tokens.TokenCookieUtil;
import com.hansang.birthday.domain.auth.dto.response.LoginResponse;
import com.hansang.birthday.domain.auth.service.KakaoService;
import com.hansang.birthday.domain.auth.userdetail.CustomUserDetails;
import com.hansang.birthday.global.error.exception.BusinessException;
import com.hansang.birthday.global.error.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth-user")
@Tag(name = "인증 (Auth)", description = "로그인, 로그아웃, 토큰 재발급 등 인증 관련 API")
public class AuthController {
    private final KakaoService kakaoService;
    private final AuthService authService;

    @Operation(
            summary = "카카오 소셜 로그인 API",
            description = "카카오 OAuth 인증 코드를 받아 사용자 로그인을 처리하고 JWT 토큰을 발급 " +
                    "Refresh Token은 HttpOnly 쿠키로 설정되며, Access Token은 응답 본문에 포함")
    @PostMapping("/kakao-login")
    public ResponseEntity<LoginResponse> kakaoLogin(
            @Parameter(description = "카카오 OAuth 인증 과정에서 받은 authorization code",required = true)
            @RequestBody KakaoLoginRequest request) {
        try{
            LoginResponse fullResponse = kakaoService.kakaoLogin(request.getCode());
            ResponseCookie responseCookie = TokenCookieUtil.createRefreshTokenCookie(
                    fullResponse.getAuthToken().getRefreshToken());
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE,responseCookie.toString())
                    .body(new LoginResponse(
                            fullResponse.getUserId(),
                            fullResponse.getAuthToken().withoutRefreshToken()));
        }catch(NoSuchElementException e){
            throw new BusinessException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(
            summary = "로그아웃 API",
            description = "사용자 로그아웃을 처리: Refresh Token 쿠키를 만료"
    )
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        authService.logout(request);
        ResponseCookie expiredTokenCookie = TokenCookieUtil.createExpiredTokenCookie();
        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE,expiredTokenCookie.toString())
                .build();
    }


    @Operation(
            summary = "액세스 토큰 재발급 API",
            description = "만료된 Access Token을 Refresh Token을 사용하여 재발급" +
                    "Refresh Token은 HttpOnly 쿠키에서 자동으로 읽어옴"
    )
    @PostMapping("/reissue")
    public ResponseEntity<AuthTokens> reissue(HttpServletRequest request) {
        AuthTokens newAccessToken = authService.reissueAccessToken(request);
        return ResponseEntity.ok(newAccessToken.withoutRefreshToken());
    }

}
