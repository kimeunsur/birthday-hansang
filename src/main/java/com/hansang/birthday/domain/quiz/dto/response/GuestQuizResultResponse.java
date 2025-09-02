package com.hansang.birthday.domain.quiz.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GuestQuizResultResponse {
    //내 quiz_response_id
    private Long guestQuizId;

    //내 점수
    private int score;

    public static GuestQuizResultResponse of(Long guestQuizId, int score) {
        return GuestQuizResultResponse.builder()
                .guestQuizId(guestQuizId)
                .score(score)
                .build();
    }
    //순위 퀴즈 View 담기
    //private int rank;
}
