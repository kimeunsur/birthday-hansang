package com.hansang.birthday.domain.quiz.domain;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="guest_quiz")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuestQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestQuizId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Integer score=0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id",nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "guestQuiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuestQuizContent> contents = new ArrayList<>();

    @Builder
    public GuestQuiz(String nickname,Quiz quiz) {
        this.nickname = nickname;
        this.score = 0;
        this.quiz = quiz;
    }

    public static GuestQuiz of(String nickname, Quiz quiz) {
        return new GuestQuiz(nickname, quiz);
    }

    public GuestQuizContent addGuestQuizContent(Question question,boolean choice,boolean isCorrect) {
        GuestQuizContent guestQuizContent = GuestQuizContent.of(choice,isCorrect,question,this);
        this.contents.add(guestQuizContent);
        return guestQuizContent;
    }

    public void updateScore(Integer score) {
        this.score = score;
    }
}
