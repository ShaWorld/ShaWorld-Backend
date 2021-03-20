package com.dsm.shaworld.common.user.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class SignUpRequest {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 2, max = 8)
    private String nickname;

    @NotEmpty
    @Size(min = 8, max = 20)
    private String password;
}
