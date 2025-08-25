package com.hansang.birthday.domain.birthday.domain.repository;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {
    Boolean existsByUserAndYear(User user, Integer year);
    Optional<Birthday> findByUserAndYear(User user, Integer year);
}
