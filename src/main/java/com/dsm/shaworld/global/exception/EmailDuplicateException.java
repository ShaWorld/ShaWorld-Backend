package com.dsm.shaworld.global.exception;

import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class EmailDuplicateException extends CommonException {

    public EmailDuplicateException(String email) {
        super(
            "EMAIL_DUPLICATION",
            String.format("이미 사용중인 이메일입니다. [%s]", email),
            HttpStatus.CONFLICT
        );
    }
}
