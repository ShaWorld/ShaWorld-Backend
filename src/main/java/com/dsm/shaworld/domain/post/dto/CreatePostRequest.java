package com.dsm.shaworld.domain.post.dto;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class CreatePostRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String address;

    @Nullable
    private String detail;

    @NotBlank
    private String price;

    @NotBlank
    private LocalDateTime date;
}