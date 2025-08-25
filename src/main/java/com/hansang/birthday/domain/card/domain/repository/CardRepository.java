package com.hansang.birthday.domain.card.domain.repository;

import com.hansang.birthday.domain.card.domain.BirthdayCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<BirthdayCard, Long> {
}
