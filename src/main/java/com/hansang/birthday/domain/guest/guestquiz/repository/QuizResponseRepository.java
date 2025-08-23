package com.hansang.birthday.domain.guest.guestquiz.repository;

import com.hansang.birthday.domain.guest.guestquiz.domain.QuizResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizResponseRepository extends JpaRepository<QuizResponse, Long> {
}
