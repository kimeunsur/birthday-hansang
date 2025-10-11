package com.hansang.birthday.domain.birthday.controller;

import com.hansang.birthday.domain.auth.userdetail.CustomUserDetails;
import com.hansang.birthday.domain.birthday.dto.response.BirthdayPeriodResponse;
import com.hansang.birthday.domain.birthday.dto.response.BirthdayResponse;
import com.hansang.birthday.domain.birthday.service.BirthdayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-user/birthday")
@Tag(name = "Birthday")
public class BirthdayController {
    private final BirthdayService birthdayService;

    @Operation(summary = "생일상 생성 API")
    @PostMapping("/create")
    public ResponseEntity<BirthdayResponse> createBirthday(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        BirthdayResponse response = birthdayService.createBirthday(user.getUserId());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "이번년도 생일상 조회 API")
    @GetMapping("/get/this-year/{birthdayId}")
    public ResponseEntity<BirthdayResponse> getBirthday(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable("birthdayId") Long birthdayId
    ) {
        BirthdayResponse response = birthdayService.getBirthdayInfo(user.getUserId(), birthdayId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "생일상 전체 조회 API")
    @GetMapping("/get/all")
    public ResponseEntity<List<BirthdayResponse>> getBirthday(
            @AuthenticationPrincipal CustomUserDetails user
    ) {
        List<BirthdayResponse> response = birthdayService.getAllBirthdays(user.getUserId());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "생일상 삭제 API")
    @DeleteMapping("/delete/{birthdayId}")
    public ResponseEntity<Void> deleteBirthday(
            @PathVariable("birthdayId") Long birthdayId
    ) {
        birthdayService.deleteBirthday(birthdayId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "생일상 공개기간 조회 API")
    @GetMapping("/get/period/{birthdayId}")
    public ResponseEntity<BirthdayPeriodResponse> getBirthdayPeriod(
            @AuthenticationPrincipal CustomUserDetails user,
            @PathVariable("birthdayId") Long birthdayId
    ) {
        BirthdayPeriodResponse response = birthdayService.getBirthdayPeriod(user.getUserId(), birthdayId);
        return ResponseEntity.ok(response);
    }
}
