package com.hansang.birthday.domain.quiz.Controller;

import com.hansang.birthday.domain.auth.userdetail.CustomUserDetails;
import com.hansang.birthday.domain.quiz.dto.request.QuizRequest;
import com.hansang.birthday.domain.quiz.dto.response.QuizRankingResponse;
import com.hansang.birthday.domain.quiz.dto.response.QuizResponse;
import com.hansang.birthday.domain.quiz.service.QuestionService;
import com.hansang.birthday.domain.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-user/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final QuestionService questionService;
    @Operation(summary = "퀴즈 생성 API")
    @PostMapping("/create")
    public ResponseEntity<QuizResponse> createQuiz (
            @RequestBody @Valid QuizRequest request,
            @AuthenticationPrincipal CustomUserDetails user
            ) {
        QuizResponse response = quizService.createQuiz(request, user.getUserId());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "퀴즈 문항 삭제 API")
    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<Void> deleteQuestion (
            @Parameter(description = "삭제할 퀴즈 문항 식별 id")
            @PathVariable("questionId") Long questionId
    ) {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "퀴즈 문항 조회 API")
    @GetMapping("/get/{quizId}")
    public ResponseEntity<QuizResponse> getQuiz (
            @Parameter(description = "조회할 퀴즈 문항 식별 id")
            @PathVariable("quizId") Long quizId
    ) {
        QuizResponse response = quizService.getQuiz(quizId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "퀴즈 랭킹 조회 API")
    @GetMapping("/get/ranking/{quizId}")
    public ResponseEntity<List<QuizRankingResponse>> getRankingQuiz (
            @Parameter(description = "조회할 퀴즈 문항 식별 id")
            @PathVariable("quizId") Long quizId
    ) {
        List<QuizRankingResponse> response = quizService.getRanking(quizId);
        return ResponseEntity.ok(response);
    }
}
