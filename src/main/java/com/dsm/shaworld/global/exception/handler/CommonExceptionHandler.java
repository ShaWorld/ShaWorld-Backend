package com.dsm.shaworld.global.exception.handler;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ExceptionResponse> commonExceptionHandler(CommonException e) {
        return new ResponseEntity(
            new ExceptionResponse(e.getCode(), e.getMessage()),
            e.getStatus()
        );
    }
}
