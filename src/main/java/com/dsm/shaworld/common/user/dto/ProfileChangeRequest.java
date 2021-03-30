package com.dsm.shaworld.common.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ProfileChangeRequest {
    @NotBlank
    private String changedProfile;
}
