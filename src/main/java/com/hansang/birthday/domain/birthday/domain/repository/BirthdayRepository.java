package com.hansang.birthday.domain.birthday.domain.repository;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {
    Boolean existsByUserAndYear(User user, Integer year);
    Optional<Birthday> findByUserAndYear(User user, Integer year);
    Optional<Birthday> findByCode(String code);
    List<Birthday> findAllByUser(User user);

    @Query("""
    SELECT b.id
    FROM Birthday b
    WHERE b.user.userId = :userId
      AND b.year = :year
""")
    Optional<Long> findIdByUserIdAndYear(Long userId, int year);
}
