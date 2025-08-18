package com.hansang.birthday.domain.quiz.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class QuizRequest {
    @NotNull
    private Long birthdayId;
    @Valid @NotEmpty
    private List<QuestionCreate> questions;

    @Getter
    public static class QuestionCreate {
        @NotNull
        private Integer sequence;
        @NotBlank
        @Size(max = 2000)
        private String content;
        @NotNull
        private Boolean answer;
    }
}
