package com.hansang.birthday.domain.quiz.domain.repository.impl;

import com.hansang.birthday.domain.quiz.domain.QGuestQuiz;
import com.hansang.birthday.domain.quiz.domain.QGuestQuizContent;
import com.hansang.birthday.domain.quiz.domain.repository.CustomGuestQuizRepository;
import com.hansang.birthday.domain.quiz.dto.response.QuizRankingRow;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CustomGuestQuizRepositoryImpl implements CustomGuestQuizRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<QuizRankingRow> findBoardByQuizId(Long quizId) {
        QGuestQuiz gq = QGuestQuiz.guestQuiz;
        QGuestQuizContent gqc = QGuestQuizContent.guestQuizContent;

        return queryFactory
                .select(Projections.constructor(
                        QuizRankingRow.class,
                        gq.guestQuizId,
                        gq.nickname,
                        gqc.guestQuizContentId.count().intValue(),
                        new CaseBuilder()
                                .when(gqc.isCorrect.isTrue()).then(1)
                                .otherwise(0)
                                .sum()
                                .intValue()
                ))
                .from(gq)
                .leftJoin(gq.contents, gqc)
                .where(gq.quiz.id.eq(quizId))
                .groupBy(gq.guestQuizId, gq.nickname, gq.score)
                .orderBy(
                        new CaseBuilder().when(gqc.isCorrect.isTrue()).then(1).otherwise(0).sum().desc(),
                        gq.guestQuizId.asc()
                )
                .fetch();
    }
}
