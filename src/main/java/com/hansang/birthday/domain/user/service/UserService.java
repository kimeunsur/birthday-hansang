package com.hansang.birthday.domain.user.service;

import com.hansang.birthday.domain.user.domain.User;
import com.hansang.birthday.domain.user.dto.request.NicknameRequest;
import com.hansang.birthday.domain.user.dto.response.UserInformationResponse;
import com.hansang.birthday.domain.user.repository.UserRepository;
import com.hansang.birthday.global.error.exception.BusinessException;
import com.hansang.birthday.global.error.exception.ErrorCode;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserInformationResponse getUserInformation(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        return UserInformationResponse.of(user);
    }

    @Transactional
    public void changeNickname(Long userId, NicknameRequest nicknameRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        if(!user.getName().equals(nicknameRequest.getNickname())){
            user.updateNickname(nicknameRequest.getNickname());
        }

    }
}
