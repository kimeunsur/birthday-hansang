package com.hansang.birthday.domain.card.service;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.birthday.domain.repository.BirthdayRepository;
import com.hansang.birthday.domain.birthday.exception.BirthdayException;
import com.hansang.birthday.domain.card.domain.BirthdayCard;
import com.hansang.birthday.domain.card.dto.CardRequest;
import com.hansang.birthday.domain.card.domain.repository.CardRepository;
import com.hansang.birthday.domain.image.domain.Image;
import com.hansang.birthday.domain.image.domain.repository.ImageRepository;
import com.hansang.birthday.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final BirthdayRepository birthdayRepository;
    private final ImageRepository imageRepository;

    public void registerCard(Long birthdayId,String nickname, CardRequest cardRequest) {
        Birthday birthday = birthdayRepository.findById(birthdayId)
                .orElseThrow(() -> new BirthdayException(ErrorCode.BIRTHDAY_NOT_FOUND));
        Image image = imageRepository.findImageByUrl(cardRequest.getImageUrl()).orElse(null);
        BirthdayCard birthdayCard = BirthdayCard.of(nickname, cardRequest.getMessageText(), image, birthday);
        cardRepository.save(birthdayCard);
    }
}
