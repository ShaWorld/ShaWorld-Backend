package com.dsm.shaworld.domain.post.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class GetLatestPostsResponse {
    @NotBlank
    private int postId;

    @Nullable
    private String postThumbnail;

    @NotBlank
    private String postTitle;

    @NotBlank
    private String postAuthor;

    @NotBlank
    private String postAddress;

    @NotBlank
    private int postPrice;
}
