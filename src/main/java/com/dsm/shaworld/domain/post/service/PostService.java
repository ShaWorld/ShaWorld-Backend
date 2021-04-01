package com.dsm.shaworld.domain.post.service;

import com.dsm.shaworld.domain.post.dto.CreatePostRequest;
import com.dsm.shaworld.domain.post.repository.PostRepository;
import com.dsm.shaworld.global.authorization.JwtProvider;
import com.dsm.shaworld.global.s3service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final JwtProvider jwtProvider;
    private final S3Service s3Service;

    public void createPost(String token, CreatePostRequest request) {
        return;
    }

}
