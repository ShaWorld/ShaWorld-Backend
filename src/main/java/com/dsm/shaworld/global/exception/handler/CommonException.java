package com.dsm.shaworld.global.exception.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException {
    private String code;
    private String message;
    private HttpStatus status;

    public CommonException(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }


}
