package com.dsm.shaworld.domain.post.dto;

import com.dsm.shaworld.domain.user.dto.UserInfoResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetPostResponse {
    private String postThumbnail;

    private String postTitle;

    private String postAuthor;

    private String postAddress;

    private String postDetail;

    private int postPrice;

    private LocalDateTime postDate;
}
