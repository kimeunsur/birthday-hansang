package com.hansang.birthday.domain.guest.guestquiz.domain;
import com.hansang.birthday.domain.quiz.domain.repository.Quiz;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="quiz_response")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResponseId;

    private String nickname;

    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

}
