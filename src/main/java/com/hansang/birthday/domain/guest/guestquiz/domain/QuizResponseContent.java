package com.hansang.birthday.domain.guest.guestquiz.domain;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name="quiz_response_content")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponseContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResponseContentId;

    private Boolean choice;
    private Boolean isCorrect;

    private String question_id; //(FK by question)

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_response_id")
    private QuizResponse quizResponse;

}
