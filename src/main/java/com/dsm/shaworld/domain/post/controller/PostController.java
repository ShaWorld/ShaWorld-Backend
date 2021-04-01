package com.dsm.shaworld.domain.post.controller;

import com.dsm.shaworld.domain.post.dto.CreatePostRequest;
import com.dsm.shaworld.domain.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void createPost(@RequestHeader(value="Authorization") String token, @RequestBody @Valid CreatePostRequest request) {
        postService.createPost(token, request);
    }
}
