package com.hansang.birthday.domain.user.controller;

import com.hansang.birthday.domain.auth.userdetail.CustomUserDetails;
import com.hansang.birthday.domain.user.dto.request.NicknameRequest;
import com.hansang.birthday.domain.user.dto.response.UserInformationResponse;
import com.hansang.birthday.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserInformationResponse> getUserInformation(
            @AuthenticationPrincipal CustomUserDetails user){
        UserInformationResponse userInformation = userService.getUserInformation(user.getUserId());
        return ResponseEntity.ok(userInformation);
    }

    @PatchMapping("/me/nickname")
    public ResponseEntity<Void> changeNickname(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody NicknameRequest nicknameRequest) {
        userService.changeNickname(user.getUserId(),nicknameRequest);
        return ResponseEntity.noContent().build();

    }

}
