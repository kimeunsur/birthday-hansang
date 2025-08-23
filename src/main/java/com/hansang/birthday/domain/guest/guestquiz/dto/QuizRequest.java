package com.hansang.birthday.domain.guest.guestquiz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizRequest {
    private String nickname;
    private List<QuestionRequest> questions;
}
