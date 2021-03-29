package com.dsm.shaworld.common.user.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class ChangeInfoRequest {
    @Size(min = 2, max = 8)
    private String changedNickname;

    @Pattern(regexp = "^[a-z0-9!@#$%^&*]{8,20}$")
    private String changedPassword;

    private String changedProfile;
}
