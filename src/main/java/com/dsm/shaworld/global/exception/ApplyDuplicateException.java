package com.dsm.shaworld.global.exception;

import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class ApplyDuplicateException extends CommonException {

    public ApplyDuplicateException() {
        super(
            "APPLY_DUPLICATION",
            "이미 신청했습니다.",
            HttpStatus.CONFLICT
        );
    }
}
