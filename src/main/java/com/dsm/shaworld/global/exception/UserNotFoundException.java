package com.dsm.shaworld.global.exception;

import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CommonException {

    public UserNotFoundException(String email) {
        super(
            "USER_NOT_FOUND",
            String.format("사용자를 찾을 수 없습니다. [%s]", email),
            HttpStatus.NOT_FOUND
        );
    }
}
