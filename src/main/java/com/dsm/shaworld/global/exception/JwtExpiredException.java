package com.dsm.shaworld.global.exception;

import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class JwtExpiredException extends CommonException {

    public JwtExpiredException() {
        super(
            "TOKEN_EXPIRATION",
            "만료된 토큰입니다.",
            HttpStatus.UNAUTHORIZED
        );
    }
}
