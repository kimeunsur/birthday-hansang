package com.hansang.birthday.domain.card.controller;


import com.hansang.birthday.domain.card.service.CardService;
import com.hansang.birthday.domain.card.dto.request.BirthdayCardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-guest/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;
    @PostMapping
    public ResponseEntity<Void> addCard(@AuthenticationPrincipal Jwt jwt,
                                        @RequestBody BirthdayCardRequest birthdayCardRequest) {
        String nickname = jwt.getClaim("nickname").toString();
        Long birthdayId = Long.valueOf(jwt.getClaim("birthdayId").toString());
        cardService.registerCard(birthdayId,nickname, birthdayCardRequest);
        return ResponseEntity.ok().build();
    }

}
