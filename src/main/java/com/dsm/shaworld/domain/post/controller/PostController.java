package com.dsm.shaworld.domain.post.controller;

import com.dsm.shaworld.domain.post.dto.CreatePostRequest;
import com.dsm.shaworld.domain.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createPost(@RequestHeader(value="Authorization") String token, @RequestBody @Valid CreatePostRequest request, @RequestPart(name="thumbnail") @Valid MultipartFile file) throws IOException {
        System.out.println(file);
        postService.createPost(token, request, file);
    }
}
