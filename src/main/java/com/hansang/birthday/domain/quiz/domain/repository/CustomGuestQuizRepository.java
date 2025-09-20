package com.hansang.birthday.domain.quiz.domain.repository;

import com.hansang.birthday.domain.quiz.dto.response.QuizRankingRow;

import java.util.List;

public interface CustomGuestQuizRepository {
    List<QuizRankingRow> findBoardByQuizId(Long quizId);
}
