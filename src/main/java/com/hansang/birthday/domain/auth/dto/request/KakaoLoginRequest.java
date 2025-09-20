package com.hansang.birthday.domain.auth.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class KakaoLoginRequest {
    @Schema(description = "카카오 OAuth 인증 코드", example = "abcd1234efgh5678")
    private String code;
}
