package com.hansang.birthday.domain.auth.controller;


import com.hansang.birthday.domain.auth.dto.request.GuestRequest;
import com.hansang.birthday.domain.auth.service.GuestAuthService;
import com.hansang.birthday.domain.auth.tokens.AuthTokens;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
}
