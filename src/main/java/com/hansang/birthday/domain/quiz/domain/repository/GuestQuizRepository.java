package com.hansang.birthday.domain.quiz.domain.repository;

import com.hansang.birthday.domain.quiz.domain.GuestQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestQuizRepository extends JpaRepository<GuestQuiz, Long>, CustomGuestQuizRepository {
}
