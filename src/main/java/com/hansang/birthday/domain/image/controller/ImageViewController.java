package com.hansang.birthday.domain.image.controller;

import com.hansang.birthday.domain.image.dto.response.ImageResponse;
import com.hansang.birthday.domain.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-guest/image")
@RequiredArgsConstructor
@Tag(name = "생일편지 등록용 이미지 조회", description = "게스트가 생일편지에 쓸 아이콘 이미지 전체 목록 조회하는 API")
public class ImageViewController {
    private final ImageService imageService;

    @Operation(summary = "생일편지 이미지 전체 조회 API")
    @GetMapping
    public ResponseEntity<List<ImageResponse>> getImage(
            @Parameter(
                    description = "Authorization 헤더를 통해 전달된 게스트 JWT 토큰에서 추출된 정보",
                    hidden = true
            )
            @AuthenticationPrincipal Jwt jwt) {
        List<ImageResponse> allImage = imageService.getAllImage();
        return ResponseEntity.ok(allImage);
    }
}
