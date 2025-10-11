package com.hansang.birthday.domain.user.controller;

import com.hansang.birthday.domain.auth.userdetail.CustomUserDetails;
import com.hansang.birthday.domain.user.dto.request.NicknameRequest;
import com.hansang.birthday.domain.user.dto.response.UserInformationResponse;
import com.hansang.birthday.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "로그인한 사용자 정보 조회 API", description = "로그인한 사용자의 닉네임, 생일, 프로필 사진 반환")
    @GetMapping("/me")
    public ResponseEntity<UserInformationResponse> getUserInformation(
            @AuthenticationPrincipal CustomUserDetails user){
        UserInformationResponse userInformation = userService.getUserInformation(user.getUserId());
        return ResponseEntity.ok(userInformation);
    }

    @PatchMapping("/me/nickname")
    @Operation(summary = "닉네임 변경 API", description = "로그인한 사용자의 닉네임을 수정")
    public ResponseEntity<Void> changeNickname(
            @AuthenticationPrincipal CustomUserDetails user,
            @Parameter(description = "변경할 닉네임 정보", required = true)
            @RequestBody NicknameRequest nicknameRequest) {
        userService.changeNickname(user.getUserId(),nicknameRequest);
        return ResponseEntity.noContent().build();

    }

}
