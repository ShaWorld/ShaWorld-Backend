package com.dsm.shaworld.domain.post.controller;

import com.dsm.shaworld.domain.post.dto.GetLatestPostsResponse;
import com.dsm.shaworld.domain.post.dto.GetPostResponse;
import com.dsm.shaworld.domain.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;

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
    public void createPost(
        @RequestHeader(value="Authorization") String token,
        @RequestParam(value="title") String title,
        @RequestParam(value="address") String address,
        @RequestParam(value="detail") String detail,
        @RequestParam(value="price") int price,
        @RequestParam(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
        @RequestPart(name="thumbnail") @Valid MultipartFile file
    ) throws IOException {
        postService.createPost(token, title, address, detail, price, date, file);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(value = HttpStatus.OK)
    public GetPostResponse getPost(
        @RequestHeader(value="Authorization") String token,
        @PathVariable(value = "postId") int postId
    ) {
        return postService.getPost(token, postId);
    }

    @GetMapping("/get-latest-posts")
    @ResponseStatus(value = HttpStatus.OK)
    public Page<GetLatestPostsResponse> getPosts(@RequestHeader(value="Authorization") String token, Pageable pageable) {
        return postService.getLatestPosts(token, pageable);
    }
}
