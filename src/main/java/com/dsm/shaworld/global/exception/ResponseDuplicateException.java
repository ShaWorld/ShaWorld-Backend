package com.dsm.shaworld.global.exception;

import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class ResponseDuplicateException extends CommonException {

    public ResponseDuplicateException() {
        super(
                "RESPONSE_DUPLICATION",
                "이미 응답한 신청입니다.",
                HttpStatus.CONFLICT
        );
    }
}
