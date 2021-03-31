package com.dsm.shaworld.domain.user.dto;

import lombok.*;

@Getter
@Builder
public class SignInResponse {
    private String token;
}
