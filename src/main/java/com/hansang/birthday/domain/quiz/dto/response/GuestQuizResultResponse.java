package com.hansang.birthday.domain.quiz.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GuestQuizResultResponse {
    private Long guestQuizId;
    private int score;
    private List<QuizRankingResponse> ranking;

    public static GuestQuizResultResponse of(Long guestQuizId, int score, List<QuizRankingResponse> ranking) {
        return GuestQuizResultResponse.builder()
                .guestQuizId(guestQuizId)
                .score(score)
                .ranking(ranking)
                .build();
    }

}
