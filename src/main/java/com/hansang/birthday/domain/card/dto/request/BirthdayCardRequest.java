package com.hansang.birthday.domain.card.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BirthdayCardRequest {
    private String messageText;
    private String imageUrl;
}
