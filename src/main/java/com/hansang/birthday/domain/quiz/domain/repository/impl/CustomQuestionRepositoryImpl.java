package com.hansang.birthday.domain.quiz.domain.repository.impl;

import com.hansang.birthday.domain.quiz.domain.QQuestion;
import com.hansang.birthday.domain.quiz.domain.Quiz;
import com.hansang.birthday.domain.quiz.domain.repository.CustomQuestionRepository;
import com.hansang.birthday.domain.quiz.dto.response.QuestionResponse;
import lombok.RequiredArgsConstructor;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class CustomQuestionRepositoryImpl implements CustomQuestionRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<QuestionResponse> getQuestionByQuiz(Quiz quiz) {
        QQuestion question = QQuestion.question;

        return queryFactory
                .selectFrom(question)
                .where(question.quiz.eq(quiz))
                .orderBy(question.sequence.asc(), question.id.asc())
                .fetch()
                .stream()
                .map(QuestionResponse::from)  // DTO 변환
                .collect(Collectors.toList());
    }
}
