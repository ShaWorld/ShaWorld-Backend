package com.dsm.shaworld.common.user.dto;

import lombok.*;

@Getter
@Builder
public class UserInfoResponse {
    private String userEmail;

    private String userNickname;

    private String userProfile;
}