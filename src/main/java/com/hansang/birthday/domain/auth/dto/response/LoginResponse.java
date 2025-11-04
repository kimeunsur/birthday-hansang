package com.hansang.birthday.domain.auth.dto.response;


import com.hansang.birthday.domain.auth.tokens.AuthTokens;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class LoginResponse {
    private Long userId;
    private AuthTokens authToken;
    private Long birthdayId;
    private Long quizId;

    public LoginResponse(Long userId, AuthTokens authToken, Long birthdayId, Long quizId) {
        this.userId = userId;
        this.authToken = authToken;
        this.birthdayId = birthdayId;
        this.quizId = quizId;
    }
}
