package com.hansang.birthday.domain.quiz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizRankingResponse {
    private final Integer rank;
    private final Long guestQuizId;
    private final String nickName;
    private final Integer correctCount;
    private final Integer totalCount;
}
