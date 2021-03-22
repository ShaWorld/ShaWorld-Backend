package com.dsm.shaworld.global.exception.handler;

import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse runtimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return new ExceptionResponse("INTERNAL_SERVER_ERROR", "문제가 발생하였다.");
    }
}
