package com.hansang.birthday.domain.auth.service;


import com.hansang.birthday.domain.auth.dto.request.GuestRequest;
import com.hansang.birthday.domain.auth.tokens.AuthTokens;
import com.hansang.birthday.domain.auth.tokens.AuthTokensGenerator;
import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.birthday.domain.repository.BirthdayRepository;
import com.hansang.birthday.domain.birthday.exception.BirthdayException;
import com.hansang.birthday.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GuestAuthService {
    private final AuthTokensGenerator authTokensGenerator;
    private final BirthdayRepository birthdayRepository;
    public AuthTokens generateGuestToken(GuestRequest guestRequest) {
        Birthday birthday = birthdayRepository.findByCode(guestRequest.getCode())
                .orElseThrow(() -> new BirthdayException(ErrorCode.BIRTHDAY_NOT_FOUND));
        if(!birthday.getIsOpen()){
            throw new BirthdayException(ErrorCode.EXPIRED_BIRTHDAY_ACCESS);
        }
        return authTokensGenerator.generateGuestToken(guestRequest.getNickname(), birthday.getId());
    }
}
