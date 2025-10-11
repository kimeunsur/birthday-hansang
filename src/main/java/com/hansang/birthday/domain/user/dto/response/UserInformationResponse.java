package com.hansang.birthday.domain.user.dto.response;

import com.hansang.birthday.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
public class UserInformationResponse {
    private String name;
    private LocalDate birthday;
    private String profileImageUrl;

    public static UserInformationResponse of(User user){
        return UserInformationResponse.builder()
                .name(user.getName())
                .birthday(user.getBirthday())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
