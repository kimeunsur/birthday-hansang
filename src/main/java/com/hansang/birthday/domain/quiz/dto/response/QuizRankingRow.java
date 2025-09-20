package com.hansang.birthday.domain.quiz.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizRankingRow {
    private final Long guestQuizId;
    private final String nickName;
    private final Integer totalCount;
    private final Integer correctCount;
}
