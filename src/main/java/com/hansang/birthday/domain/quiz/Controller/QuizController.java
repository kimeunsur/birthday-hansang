package com.hansang.birthday.domain.quiz.Controller;

import com.hansang.birthday.domain.auth.userdetail.CustomUserDetails;
import com.hansang.birthday.domain.quiz.dto.request.QuizRequest;
import com.hansang.birthday.domain.quiz.dto.response.QuizResponse;
import com.hansang.birthday.domain.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-user/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @Operation(summary = "퀴즈 생성 API")
    @PostMapping("/create")
    public ResponseEntity<QuizResponse> createQuiz (
            @RequestBody @Valid QuizRequest request,
            @AuthenticationPrincipal CustomUserDetails user
            ) {
        QuizResponse response = quizService.createQuiz(request, user.getUserId());
        return ResponseEntity.ok(response);
    }
}
