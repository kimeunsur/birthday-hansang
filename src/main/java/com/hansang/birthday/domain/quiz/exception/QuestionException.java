package com.hansang.birthday.domain.quiz.exception;

import com.hansang.birthday.global.error.exception.BusinessException;
import com.hansang.birthday.global.error.exception.ErrorCode;

public class QuestionException extends BusinessException {
    public QuestionException(ErrorCode errorCode) {
        super(errorCode);
    }
}
