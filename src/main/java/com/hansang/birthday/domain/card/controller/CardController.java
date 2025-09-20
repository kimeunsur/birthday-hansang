package com.hansang.birthday.domain.card.controller;


import com.hansang.birthday.domain.card.service.CardService;
import com.hansang.birthday.domain.card.dto.request.BirthdayCardRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-guest/card")
@RequiredArgsConstructor
@Tag(name = "게스트 축하카드 (Guest Card)", description = "게스트 사용자가 생일상에 축하 카드를 등록할 수 있는 API")
public class CardController {
    private final CardService cardService;


    @Operation(summary = "생일상 카드 등록 API")
    @PostMapping
    public ResponseEntity<Void> addCard(
            @Parameter(
                    description = "Authorization 헤더를 통해 전달된 게스트 JWT 토큰에서 추출된 정보",
                    hidden = true
            )
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(
                    description = "축하 카드 등록을 위한 요청 정보",
                    required = true,
                    schema = @Schema(implementation = BirthdayCardRequest.class)
            )
            @RequestBody BirthdayCardRequest birthdayCardRequest) {
        String nickname = jwt.getClaim("nickname").toString();
        Long birthdayId = Long.valueOf(jwt.getClaim("birthdayId").toString());
        cardService.registerCard(birthdayId,nickname, birthdayCardRequest);
        return ResponseEntity.ok().build();
    }

}
