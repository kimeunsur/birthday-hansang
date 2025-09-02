package com.hansang.birthday.domain.birthday.service;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.birthday.domain.repository.BirthdayRepository;
import com.hansang.birthday.domain.birthday.dto.response.BirthdayResponse;
import com.hansang.birthday.domain.birthday.exception.BirthdayException;
import com.hansang.birthday.domain.user.domain.User;
import com.hansang.birthday.domain.user.exception.UserException;
import com.hansang.birthday.domain.user.repository.UserRepository;
import com.hansang.birthday.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class BirthdayService {
    private final BirthdayRepository birthdayRepository;
    private final UserRepository userRepository;

    public BirthdayResponse createBirthday(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        Integer year = LocalDate.now().getYear();
        if (birthdayRepository.existsByUserAndYear(user, year)) {
            throw new BirthdayException(ErrorCode.BIRTHDAY_ALREADY_EXISTS);
        }
        String code = UUID.randomUUID().toString();
        Birthday birthday = Birthday.of(year, user, code);
        birthdayRepository.save(birthday);
        return BirthdayResponse.of(user.getUserId(), birthday);
    }

    public BirthdayResponse getBirthdayInfo(Long userId, Long birthdayId) {
        Integer year = LocalDate.now().getYear();
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        Birthday birthday = birthdayRepository.findByUserAndYear(user, year)
                .orElseThrow(() -> new BirthdayException(ErrorCode.BIRTHDAY_NOT_FOUND));
        return BirthdayResponse.of(userId, birthday);
    }
  
    public BirthdayResponse getBirthdayInfoByBirthdayId(Long birthdayId) {
        Birthday birthday = birthdayRepository.findById(birthdayId)
                .orElseThrow(() -> new BirthdayException(ErrorCode.BIRTHDAY_NOT_FOUND));
        if(!birthday.getIsOpen()) throw new BirthdayException(ErrorCode.EXPIRED_BIRTHDAY_ACCESS);
        return BirthdayResponse.of(birthday.getUser().getUserId(), birthday);
    }

    public List<BirthdayResponse> getAllBirthdays(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        List<Birthday> birthdays = birthdayRepository.findAllByUser(user);
        return birthdays.stream()
                .map(birthday -> BirthdayResponse.of(userId, birthday))
                .toList();
    }
}
