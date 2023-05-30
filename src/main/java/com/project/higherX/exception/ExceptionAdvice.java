package com.project.higherX.exception;

import com.project.higherX.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    // 500 에러
    @ExceptionHandler(IllegalArgumentException.class) // 지정한 예외가 발생하면 해당 메소드 실행
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 각 예외마다 상태 코드 지정
    public Response illegalArgumentExceptionAdvice(IllegalArgumentException e) {
        return Response.failure(500, e.getMessage().toString());
    }

    // 401 응답
    // 아이디 혹은 비밀번호 오류시 발생
    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response loginFailureException() {
        return Response.failure(401, "로그인에 실패하였습니다.");
    }

    // 401 응답
    // 요청자와 요청한 유저의 정보가 일치하지 않을시에 발생
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response usernameNotFoundException() {
        return Response.failure(401, "유저 정보가 일치하지 않습니다.");
    }

    // 401 응답
    // Todo를 찾을 수 없음
    @ExceptionHandler(TodoNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Response todoNotFoundException() {
        return Response.failure(401, "요청한 Todo를 찾을 수 없습니다.");
    }


    // 404 응답
    // 요청한 유저를 찾을 수 없음
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response userNotFoundException() {
        return Response.failure(404, "요청한 유저를 찾을 수 없습니다.");
    }

    // 409 응답
    // account 중복
    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response accountAlreadyExistsException() {
        return Response.failure(409,  "중복된 계정 입니다.");
    }
    // 409 응답
    // 사업자번호 유효성 실패
    @ExceptionHandler(CrnInvalidException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response crnInvalidException() {
        return Response.failure(409,  "유효하지 않는 사업자번호 입니다.");
    }

    // 409 응답
    // nickname 중복
    @ExceptionHandler(NicknameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Response nicknameAlreadyExistsException() {
        return Response.failure(409, "중복된 닉네임 입니다.");
    }

    // 400 에러
    // 토큰 만료
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response tokenExpiredException() {
        return Response.failure(400, "토큰이 만료되었습니다.");
    }

    // 400 에러
    // 토큰 유효성
    @ExceptionHandler(TokenInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response tokenInvalidException() {
        return Response.failure(400, "유효하지 않는 토큰입니다.");
    }
}
