package com.project.higherX.exception;

import lombok.Getter;

@Getter
public enum ExceptionType {

    EXCEPTION("exception.code", "exception.msg"),
    AUTHENTICATION_ENTRY_POINT_EXCEPTION("authenticationEntryPointException.code", "authenticationEntryPointException.msg"),
    ACCESS_DENIED_EXCEPTION("accessDeniedException.code", "accessDeniedException.msg"),
    LOGIN_FAILURE_EXCEPTION("loginFailureException.code", "loginFailureException.msg"),
    USER_NAME_NOT_FOUNT_EXCEPTION("usernameNotFoundException.code", "usernameNotFoundException.msg"),
    TODO_NOT_FOUND_EXCEPTION("todoNotFoundException.code", "todoNotFoundException.msg"),
    USER_NOT_FOUND_EXCEPTION("userNotFoundException.code", "userNotFoundException.msg"),
    TOKEN_EXPIRED_EXCEPTION_EXCEPTION("tokenExpiredException.code", "tokenExpiredException.msg"),
    TOKEN_INVALID_EXCEPTION_EXCEPTION("tokenInvalidException.code", "tokenInvalidException.msg"),
    ACCOUNT_ALREADY_EXISTS_EXCEPTION("accountAlreadyExistsException.code", "accountAlreadyExistsException.msg"),
    NICK_NAME_ALREADY_EXISTS_EXCEPTION("nicknameAlreadyExistsException.code", "nicknameAlreadyExistsException.msg");

    private final String code;
    private final String message;

    ExceptionType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
