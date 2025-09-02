package com.hansang.birthday.domain.birthday.controller;


import com.hansang.birthday.domain.birthday.dto.response.BirthdayResponse;
import com.hansang.birthday.domain.birthday.service.BirthdayService;
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
@Tag(name = "Guest-Birthday")
public class GuestBirthdayController {
    private final BirthdayService birthdayService;

    @GetMapping()
    public ResponseEntity<BirthdayResponse> getBirthday(@AuthenticationPrincipal Jwt jwt) {
        BirthdayResponse response = birthdayService.getBirthdayInfoByBirthdayId(jwt.getClaim("birthdayId"));
        return ResponseEntity.ok(response);
    }

}
