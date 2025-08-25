package com.hansang.birthday.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class GuestRequest {
    String code;
    String nickname;
}
