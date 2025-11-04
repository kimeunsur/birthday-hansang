package com.hansang.birthday.domain.birthday.dto.response;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class BirthdayPeriodResponse {
    private Long birthdayId;
    private LocalDate startTime;
    private LocalDate endTime;

    public static BirthdayPeriodResponse of(Birthday birthday, User user) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        LocalDate startTime = LocalDate.of(today.getYear(), 1, 1);
        LocalDate endTime   = LocalDate.of(year, 12, 31);
        return BirthdayPeriodResponse.builder()
                .birthdayId(birthday.getId())
                .startTime(startTime)
                .endTime(endTime)
                .build();

    }
}