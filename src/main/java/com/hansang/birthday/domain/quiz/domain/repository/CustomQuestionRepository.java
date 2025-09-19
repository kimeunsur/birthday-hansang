package com.hansang.birthday.domain.quiz.domain.repository;

import com.hansang.birthday.domain.quiz.domain.Quiz;
import com.hansang.birthday.domain.quiz.dto.response.QuestionResponse;

import java.util.List;

public interface CustomQuestionRepository {
    List<QuestionResponse> getQuestionByQuiz(Quiz quiz);
}
