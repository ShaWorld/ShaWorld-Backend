package com.dsm.shaworld.global.exception;

import com.dsm.shaworld.global.exception.handler.CommonException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends CommonException {

    public PostNotFoundException(int postId) {
        super(
            "POST_NOT_FOUND",
            String.format("게시물을 찾을 수 없습니다. [%d]", postId),
            HttpStatus.NOT_FOUND
        );
    }
}
