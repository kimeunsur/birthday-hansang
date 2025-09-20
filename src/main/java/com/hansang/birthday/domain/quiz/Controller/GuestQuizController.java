package com.hansang.birthday.domain.quiz.Controller;

import com.hansang.birthday.domain.quiz.dto.request.GuestQuestionRequest;
import com.hansang.birthday.domain.quiz.dto.response.GuestQuizResultResponse;
import com.hansang.birthday.domain.quiz.dto.response.QuizRankingResponse;
import com.hansang.birthday.domain.quiz.dto.response.QuizResponse;
import com.hansang.birthday.domain.quiz.service.GuestQuizService;
import com.hansang.birthday.domain.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-guest/quiz")
@RequiredArgsConstructor
public  class GuestQuizController {
    private final GuestQuizService guestQuizService;
    private final QuizService quizService;

    //방문자 퀴즈 랭킹 보기 api 추가하기
    @Operation(summary = "게스트 퀴즈 랭킹 조회 API")
    @GetMapping("/get/ranking/{quizId}")
    public ResponseEntity<List<QuizRankingResponse>> getRankingQuiz (
            @Parameter(description = "조회할 퀴즈 문항 식별 id")
            @PathVariable("quizId") Long quizId
    ) {
        List<QuizRankingResponse> response = quizService.getRanking(quizId);
        return ResponseEntity.ok(response);
    }

    //방문자 퀴즈 문항 보기 api 추가하기
    @Operation(summary = "퀴즈 문항 조회 API")
    @GetMapping("/get/{quizId}")
    public ResponseEntity<QuizResponse> getQuiz (
            @Parameter(description = "조회할 퀴즈 문항 식별 id")
            @PathVariable("quizId") Long quizId
    ) {
        QuizResponse response = quizService.getQuiz(quizId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "게스트 퀴즈 결과 등록 API")
    @PostMapping("/submit/{quizId}")
    public ResponseEntity<GuestQuizResultResponse> submit(
            @AuthenticationPrincipal Jwt jwt,
            @Parameter(description = "게스트 퀴즈 풀이 내용 등록할 퀴즈 문항 식별 id")
            @PathVariable Long quizId,
            @Parameter(description = "게스트 퀴즈 풀이 내용")
            @RequestBody List<GuestQuestionRequest> guestQuizRequest) {
        String nickname = jwt.getClaim("nickname");
        GuestQuizResultResponse response = guestQuizService.registerQuiz(nickname, quizId, guestQuizRequest);
        return ResponseEntity.ok(response);
    }
}