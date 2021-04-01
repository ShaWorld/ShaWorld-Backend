package com.dsm.shaworld.domain.post.service;

import com.dsm.shaworld.domain.post.entity.Post;
import com.dsm.shaworld.domain.post.repository.PostRepository;
import com.dsm.shaworld.domain.user.entity.User;
import com.dsm.shaworld.domain.user.service.UserService;
import com.dsm.shaworld.global.s3service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {
    private final UserService userService;
    private final PostRepository postRepository;
    private final S3Service s3Service;

    public void createPost(String token, String title, String address, String detail, int price, LocalDateTime date, MultipartFile file) throws IOException {
        User user = userService.getInfoByTokenForServer(token);

        String thumbnailPath = s3Service.uploadS3File(file);

        postRepository.save(
            Post.builder()
            .postThumbnail(thumbnailPath)
            .postTitle(title)
            .postAuthor(user)
            .postAddress(address)
            .postDetail(detail)
            .postPrice(price)
            .postDate(date)
            .build()
        );

        return;
    }

}
