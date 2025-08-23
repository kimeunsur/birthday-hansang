package com.hansang.birthday.domain.guest.guestquiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {
    private Long questionId;
    private boolean answer;
}
