package com.hansang.birthday.domain.birthday.dto.response;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.card.dto.response.BirthdayCardResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class GuestBirthdayResponse {
    private Long userId;
    private Long birthdayId;
    private String code;
    private List<BirthdayCardResponse> birthdayCards;
    private boolean isVisible;

    public static GuestBirthdayResponse of (Long userId, Birthday birthday) {
        boolean isVisible = birthday.getIsVisible();
        return GuestBirthdayResponse.builder()
                .userId(userId)
                .birthdayId(birthday.getId())
                .code(birthday.getCode())
                .birthdayCards(
                        birthday.getBirthdayCards().stream().map(
                                card -> {
                                    BirthdayCardResponse response = BirthdayCardResponse.of(card);
                                    if (isVisible) {
                                        return BirthdayCardResponse.builder()
                                                .birthdayCardId((response.getBirthdayCardId()))
                                                .message(null)
                                                .nickname(response.getNickname())
                                                .imageUrl(response.getImageUrl())
                                                .build();
                                    }
                                    return response;
                                }
                        ).toList()
                )
                .isVisible(isVisible)
                .build();
    }
}

