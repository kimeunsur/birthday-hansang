package com.hansang.birthday.domain.auth.controller;


import com.hansang.birthday.domain.auth.dto.request.GuestRequest;
import com.hansang.birthday.domain.auth.service.GuestAuthService;
import com.hansang.birthday.domain.auth.tokens.AuthTokens;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth-guest")
@Slf4j
@Tag(name = "게스트 인증 (Guest Auth)", description = "게스트 사용자가 생일상에 접속하기 위한 임시 토큰 발급 API")
public class GuestAuthController {
    private final GuestAuthService guestAuthService;

    @Operation(summary = "게스트 생일상 접속 API")
    @PostMapping
    public ResponseEntity<AuthTokens> generateGuestTokens(
            @Parameter(
                    description = "게스트 접속을 위한 요청 정보",
                    required = true,
                    schema = @Schema(implementation = GuestRequest.class)
            )
            @RequestBody GuestRequest guestRequest) {
        AuthTokens guestToken = guestAuthService.generateGuestToken(guestRequest);
        return ResponseEntity.ok(guestToken);
    }
}
