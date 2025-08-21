package com.hansang.birthday.domain.image.exception;

import com.hansang.birthday.global.error.exception.BusinessException;
import com.hansang.birthday.global.error.exception.ErrorCode;

public class ImageException extends BusinessException {
    public ImageException(ErrorCode errorCode) {
        super(errorCode);
    }
}
