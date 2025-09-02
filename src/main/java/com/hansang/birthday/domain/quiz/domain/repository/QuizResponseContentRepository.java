package com.hansang.birthday.domain.quiz.domain.repository;

import com.hansang.birthday.domain.quiz.domain.GuestQuizContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizResponseContentRepository extends JpaRepository<GuestQuizContent, Long> {
}
