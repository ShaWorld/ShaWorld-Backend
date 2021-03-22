package com.dsm.shaworld.common.user.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class SignInRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^[a-z0-9!@#$%^&*]{8,20}$")
    private String password;
}
