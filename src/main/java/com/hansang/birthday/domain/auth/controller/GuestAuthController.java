package com.hansang.birthday.domain.auth.controller;


import com.hansang.birthday.domain.auth.dto.request.GuestRequest;
import com.hansang.birthday.domain.auth.service.GuestAuthService;
import com.hansang.birthday.domain.auth.tokens.AuthTokens;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/guest")
@Slf4j
public class GuestAuthController {
    private final GuestAuthService guestAuthService;
    @PostMapping
    public ResponseEntity<AuthTokens> generateGuestTokens(@RequestBody GuestRequest guestRequest) {
        AuthTokens guestToken = guestAuthService.generateGuestToken(guestRequest);
        return ResponseEntity.ok(guestToken);
    }

    @Hidden
    @GetMapping("/test")
    public ResponseEntity<Void> test(@AuthenticationPrincipal Jwt jwt) {
        String role = jwt.getClaimAsString("role");
        log.info("Jwt role: {}", role);
        return ResponseEntity.ok().build();
    }
}
