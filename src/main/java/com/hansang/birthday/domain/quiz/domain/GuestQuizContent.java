package com.hansang.birthday.domain.quiz.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name="guest_quiz_content")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestQuizContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestQuizContentId;

    private boolean choice;
    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guest_quiz_id")
    private GuestQuiz guestQuiz;

    @Builder
    public GuestQuizContent(boolean choice, boolean isCorrect, Question question, GuestQuiz guestQuiz) {
        this.choice = choice;
        this.isCorrect = isCorrect;
        this.question = question;
        this.guestQuiz = guestQuiz;
    }

    public static GuestQuizContent of(boolean choice, boolean isCorrect, Question question, GuestQuiz guestQuiz) {
        return new GuestQuizContent(choice, isCorrect, question, guestQuiz);
    }


}
