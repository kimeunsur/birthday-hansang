package com.hansang.birthday.domain.quiz.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestQuestionRequest {
    @NotNull
    private Long questionId;

    @NotNull
    private boolean answer;
}
