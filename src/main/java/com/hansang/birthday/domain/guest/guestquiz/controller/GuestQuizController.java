package com.hansang.birthday.domain.guest.guestquiz.controller;

import com.hansang.birthday.domain.guest.guestquiz.dto.QuizRequest;
import com.hansang.birthday.domain.guest.guestquiz.dto.QuizResultResponse;
import com.hansang.birthday.domain.guest.guestquiz.service.GuestQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-guest/quiz/{quizId}")
@RequiredArgsConstructor
public class GuestQuizController {
    private final GuestQuizService guestQuizService;

//    @GetMapping
//    public ResponseEntity<QuizResultResponse> getQuiz(@PathVariable int quizId) {
//        //quizService에서 나온 get으로 보내기
//    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResultResponse> submit(@PathVariable Long quizId, @RequestBody QuizRequest quizRequest) {
        guestQuizService.registerQuiz(quizId,quizRequest);
        return ResponseEntity.ok(new QuizResultResponse());
    }
}
