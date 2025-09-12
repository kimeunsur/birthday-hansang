package com.hansang.birthday.global.error.exception;


import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),
    WRONG_REFRESH_TOKEN(400, "C007", "Wrong Refresh Token"),
    // USER
    USER_NOT_FOUND(400, "U001", "유저를 찾을 수 없습니다"),
    // BIRTHDAY
    BIRTHDAY_ALREADY_EXISTS(409, "B001", "생일상이 이미 존재합니다"),
    BIRTHDAY_NOT_FOUND(400, "B002", "생일상을 찾을 수 없습니다"),
    UNAUTHORIZED_BIRTHDAY_ACCESS(400, "B003", "생일상 주인이 아닙니다"),
    EXPIRED_BIRTHDAY_ACCESS(400, "B004", "만료된 생일상입니다"),
    // QUIZ
    QUIZ_ALREADY_EXISTS(409, "Q001", "퀴즈가 이미 존재합니다"),
    QUESTION_NOT_FOUND(404, "Q002", "문항을 찾을 수 없습니다"),
    QUIZ_NOT_FOUND(404,"Q003","퀴즈를 찾을 수 없습니다"),
    QUESTION_NOT_IN_QUIZ(404,"Q004","해당 문항은 생일자의 퀴즈의 문항이 아닙니다");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

}


