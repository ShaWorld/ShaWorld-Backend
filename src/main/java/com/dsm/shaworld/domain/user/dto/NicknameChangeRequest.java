package com.dsm.shaworld.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class NicknameChangeRequest {
    @NotBlank
    @Size(min = 2, max = 8)
    private String changedNickname;
}
