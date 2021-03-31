package com.dsm.shaworld.domain.user.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class PasswordChangeRequest {
    @NotBlank
    @Pattern(regexp = "^[a-z0-9!@#$%^&*]{8,20}$")
    private String currentPassword;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9!@#$%^&*]{8,20}$")
    private String changePassword;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9!@#$%^&*]{8,20}$")
    private String changePasswordConfirm;

}
