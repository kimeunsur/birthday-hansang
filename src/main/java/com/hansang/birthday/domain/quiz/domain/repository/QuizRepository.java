package com.hansang.birthday.domain.quiz.domain.repository;

import com.hansang.birthday.domain.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
