package com.hansang.birthday.domain.birthday.dto.response;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BirthdayResponse {
    private Long userId;
    private Long birthdayId;
    private String code;

    public static BirthdayResponse of (Long userId, Birthday birthday) {
        return BirthdayResponse.builder()
                .userId(userId)
                .birthdayId(birthday.getId())
                .code(birthday.getCode())
                .build();
    }
}
