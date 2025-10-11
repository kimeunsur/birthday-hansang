package com.hansang.birthday.domain.birthday.dto.response;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class BirthdayPeriodResponse {
    private Long birthdayId;
    private LocalDate startTime;
    private LocalDate endTime;

    public static BirthdayPeriodResponse of(Birthday birthday, User user) {
        return BirthdayPeriodResponse.builder()
                .birthdayId(birthday.getId())
                .startTime(user.getBirthday().minusDays(14))
                .endTime(user.getBirthday())
                .build();

    }
}
