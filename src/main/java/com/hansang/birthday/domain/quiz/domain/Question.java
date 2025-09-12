package com.hansang.birthday.domain.quiz.domain;

import com.hansang.birthday.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Boolean answer; // O=0, X=1
    @Column(nullable = false)
    private Integer sequence;

    @Builder
    public Question( Quiz quiz, String content, Integer sequence, Boolean answer) {
        this.quiz = quiz;
        this.content = content;
        this.sequence = sequence;
        this.answer = answer;
    }

    public static Question of (Quiz quiz, String content, Integer sequence, Boolean answer) {
        return Question.builder()
                .quiz(quiz)
                .content(content)
                .sequence(sequence)
                .answer(answer)
                .build();
    }
}
