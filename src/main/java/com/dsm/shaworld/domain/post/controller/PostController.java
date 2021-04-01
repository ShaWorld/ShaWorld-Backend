package com.dsm.shaworld.domain.post.controller;

import com.dsm.shaworld.domain.post.service.PostService;
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
        @RequestParam(value="date") LocalDateTime date,
        @RequestPart(name="thumbnail") @Valid MultipartFile file
    ) throws IOException {
        postService.createPost(token, title, address, detail, price, date, file);
    }
}
