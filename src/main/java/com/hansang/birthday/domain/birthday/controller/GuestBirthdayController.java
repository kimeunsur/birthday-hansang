package com.hansang.birthday.domain.birthday.controller;


import com.hansang.birthday.domain.birthday.dto.response.BirthdayResponse;
import com.hansang.birthday.domain.birthday.service.BirthdayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-guest/birthday")
@Tag(name = "게스트 생일상", description = "게스트가 초대 받은 생일상을 조회할 수 있는 API")
public class GuestBirthdayController {
    private final BirthdayService birthdayService;

    @Operation(summary = "게스트 생일상 조회")
    @GetMapping()
    public ResponseEntity<BirthdayResponse> getBirthday(
            @Parameter(
                    description = "Authorization 헤더를 통해 전달된 게스트 JWT 토큰에서 추출된 정보",
                    hidden = true
            )
            @AuthenticationPrincipal Jwt jwt) {
        BirthdayResponse response = birthdayService.getBirthdayInfoByBirthdayId(jwt.getClaim("birthdayId"));
        return ResponseEntity.ok(response);
    }

}
