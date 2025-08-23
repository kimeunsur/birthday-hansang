package com.hansang.birthday.domain.quiz.dto.response;

import com.hansang.birthday.domain.quiz.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionResponse {
    private Long questionId;
    private String content;
    private Boolean answer;
    private Integer sequence;

    public static QuestionResponse from(Question question) {
        return QuestionResponse.builder()
                .questionId(question.getId())
                .content(question.getContent())
                .answer(question.getAnswer())
                .sequence(question.getSequence())
                .build();
    }
}
