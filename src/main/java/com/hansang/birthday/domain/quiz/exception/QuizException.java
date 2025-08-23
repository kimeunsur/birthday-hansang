package com.hansang.birthday.domain.quiz.exception;

import com.hansang.birthday.global.error.exception.BusinessException;
import com.hansang.birthday.global.error.exception.ErrorCode;

public class QuizException extends BusinessException {
    public QuizException(ErrorCode errorCode) {
        super(errorCode);
    }
}
