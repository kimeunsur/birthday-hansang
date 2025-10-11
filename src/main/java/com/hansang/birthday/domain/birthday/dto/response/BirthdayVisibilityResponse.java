package com.hansang.birthday.domain.birthday.dto.response;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BirthdayVisibilityResponse {
    private Long birthdayId;
    private Boolean isVisible;
    public static BirthdayVisibilityResponse of(Birthday birthday) {
        return BirthdayVisibilityResponse.builder()
                .birthdayId(birthday.getId())
                .isVisible(birthday.getIsVisible())
                .build();
    }
}
