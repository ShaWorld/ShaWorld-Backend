package com.dsm.shaworld.domain.apply.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class GetReceiveAppliesResponse {
    @NotBlank
    private int applyId;

    @NotBlank
    private int applyPostId;

    @NotBlank
    private String applyPostTitle;

    @NotBlank
    private String applyApplicant;
}
