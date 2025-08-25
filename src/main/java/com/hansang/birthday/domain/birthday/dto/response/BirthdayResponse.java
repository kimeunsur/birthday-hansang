package com.hansang.birthday.domain.birthday.dto.response;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.card.domain.BirthdayCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class BirthdayResponse {
    private Long userId;
    private Long birthdayId;
    private String code;
    private List<BirthdayCard> birthdayCards;

    public static BirthdayResponse of (Long userId, Birthday birthday) {
        return BirthdayResponse.builder()
                .userId(userId)
                .birthdayId(birthday.getId())
                .code(birthday.getCode())
                .birthdayCards(birthday.getBirthdayCards())
                .build();
    }
}
