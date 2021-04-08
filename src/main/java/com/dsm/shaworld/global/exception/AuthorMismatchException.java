package com.dsm.shaworld.global.exception;
;
import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class AuthorMismatchException extends CommonException {
    public AuthorMismatchException(String postAuthorNickname, String userNickname) {
        super(
            "AUTHOR_MISMATCH",
            String.format("게시자와 유저가 일치하지 않습니다. [%s, %s]", postAuthorNickname, userNickname ),
            HttpStatus.BAD_REQUEST
        );
    }
}
