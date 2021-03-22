package com.dsm.shaworld.global.exception;

import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends CommonException {

    public PasswordMismatchException(String password) {
        super(
                "PASSWORD_MISMATCH",
                String.format("비밀번호가 일치하지 않습니다. [%s]", password),
                HttpStatus.BAD_REQUEST
        );
    }

    public PasswordMismatchException(String password, String passwordConfirm) {
        super(
            "PASSWORD_MISMATCH",
            String.format("비밀번호가 일치하지 않습니다. [%s, %s]", password, passwordConfirm),
            HttpStatus.BAD_REQUEST
        );
    }

}
