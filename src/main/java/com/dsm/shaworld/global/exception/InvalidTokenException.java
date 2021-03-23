package com.dsm.shaworld.global.exception;

import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends CommonException {

    public InvalidTokenException() {
        super(
            "INVALID_TOKEN",
            "유효하지 않은 토큰입니다.",
            HttpStatus.UNAUTHORIZED
        );
    }
}
