package com.hansang.birthday.domain.card.dto.response;

import com.hansang.birthday.domain.card.domain.BirthdayCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BirthdayCardResponse {
    private final Long birthdayCardId;
    private final String message;
    private final String nickname;
    private final String imageUrl;

    public static BirthdayCardResponse of(BirthdayCard birthdayCard) {
        return BirthdayCardResponse.builder()
                .birthdayCardId(birthdayCard.getId())
                .message(birthdayCard.getMessage())
                .nickname(birthdayCard.getNickName())
                .imageUrl(birthdayCard.getImage().getUrl())
                .build();
    }
}
