package com.hansang.birthday.domain.quiz.domain.repository;

import com.hansang.birthday.domain.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("""
    SELECT q.id
    FROM Quiz q
    WHERE q.birthday.user.userId = :userId
      AND q.birthday.year = :year
""")
    Optional<Long> findIdByUserIdAndYear(Long userId, int year);

}
