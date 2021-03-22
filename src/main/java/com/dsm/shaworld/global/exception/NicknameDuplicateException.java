package com.dsm.shaworld.global.exception;

import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class NicknameDuplicateException extends CommonException {

    public NicknameDuplicateException(String nickname) {
        super(
            "NICKNAME_DUPLICATION",
            String.format("이미 사용중인 닉네임입니다. [%s]", nickname),
            HttpStatus.CONFLICT
        );
    }
}
