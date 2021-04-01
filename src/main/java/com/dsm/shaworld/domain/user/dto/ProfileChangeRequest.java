package com.dsm.shaworld.domain.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProfileChangeRequest {
    @NotNull
    private MultipartFile profile;
}
