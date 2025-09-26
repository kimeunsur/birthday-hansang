package com.hansang.birthday.domain.image.dto.response;

import com.hansang.birthday.domain.card.domain.BirthdayCard;
import com.hansang.birthday.domain.card.dto.response.BirthdayCardResponse;
import com.hansang.birthday.domain.image.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ImageResponse {
    private Long imageId;
    private String imageUrl;

    public static ImageResponse of(Image image) {
        return ImageResponse.builder()
                .imageId(image.getId())
                .imageUrl(image.getUrl())
                .build();
    }
}
