package com.dsm.shaworld.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class SignUpRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 2, max = 8)
    private String nickname;

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^[a-z0-9!@#$%^&*]{8,20}$")
    private String password;

    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*]{8,20}$")
    private String passwordConfirm;
}