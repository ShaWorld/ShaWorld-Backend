package com.dsm.shaworld.domain.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetPostResponse {
    private String postThumbnail;

    private String postTitle;

    private String postAuthorProfile;

    private String postAuthorNickname;

    private String postAddress;

    private String postDetail;

    private int postPrice;

    private LocalDateTime postDate;
}
