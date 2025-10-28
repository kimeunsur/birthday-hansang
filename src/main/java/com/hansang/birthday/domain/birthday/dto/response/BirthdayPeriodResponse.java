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
        LocalDate birthdayThisYear = user.getBirthday().withYear(today.getYear());
        LocalDate startTime = LocalDate.of(today.getYear(), 1, 1);
        LocalDate endTime = birthdayThisYear;
        return BirthdayPeriodResponse.builder()
                .birthdayId(birthday.getId())
                //.startTime(birthdayThisYear.minusDays(14))
                .startTime(startTime)
                //.endTime(birthdayThisYear)
                .endTime(endTime)
                .build();

    }
}
