package com.hansang.birthday.domain.quiz.domain;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "birthday_id", nullable = false)
    private Birthday birthday;
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sequence ASC")
    private List<Question> questions = new ArrayList<>();

    @Builder
    public Quiz (Birthday birthday, List<Question> questions) {
        this.birthday = birthday;
        if (questions != null) {
            this.questions = questions;
        }
    }

    public static Quiz of(Birthday birthday) {
        Quiz quiz = Quiz.builder()
                .birthday(birthday)
                .build();
        birthday.setQuiz(quiz);
        return quiz;
    }

    public Question addQuestion(String content, Integer sequence, Boolean answer) {
        Question q = Question.of(this, content, sequence, answer);
        this.questions.add(q);
        return q;
    }
}
