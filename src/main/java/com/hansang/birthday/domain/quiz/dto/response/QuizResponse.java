package com.hansang.birthday.domain.quiz.dto.response;

import com.hansang.birthday.domain.quiz.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class QuizResponse {
    private Long quizId;
    private Long birthdayId;
    private List<QuestionResponse> questions;

    public static QuizResponse of (Quiz quiz) {
        return QuizResponse.builder()
                .quizId(quiz.getId())
                .birthdayId(quiz.getBirthday().getId())
                .questions(
                        quiz.getQuestions().stream()
                                .map(QuestionResponse::from)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
