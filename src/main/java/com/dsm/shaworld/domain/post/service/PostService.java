package com.dsm.shaworld.domain.post.service;

import com.dsm.shaworld.domain.post.dto.GetLatestPostsResponse;
import com.dsm.shaworld.domain.post.dto.GetPostResponse;
import com.dsm.shaworld.domain.post.entity.Post;
import com.dsm.shaworld.domain.post.repository.PostRepository;
import com.dsm.shaworld.domain.user.entity.User;
import com.dsm.shaworld.domain.user.service.UserService;
import com.dsm.shaworld.global.exception.AuthorMismatchException;
import com.dsm.shaworld.global.exception.PostNotFoundException;
import com.dsm.shaworld.global.s3service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        String thumbnailPath = null;

        if(file != null) {
            thumbnailPath = s3Service.uploadS3File(file);
        }

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

    public Post getPost(int postId) {
        Post post = postRepository.findByPostId(postId).orElseThrow(() -> new PostNotFoundException(postId));

        return post;
    }

    public GetPostResponse getPostDetail(int postId) {
        Post post = getPost(postId);

        return GetPostResponse.builder()
                .postThumbnail(post.getPostThumbnail())
                .postTitle(post.getPostTitle())
                .postAuthorProfile(post.getPostAuthor().getUserProfile())
                .postAuthorNickname(post.getPostAuthor().getUserNickname())
                .postAddress(post.getPostAddress())
                .postDetail(post.getPostDetail())
                .postPrice(post.getPostPrice())
                .postDate(post.getPostDate())
                .build();
    }

    public Page<GetLatestPostsResponse> getLatestPosts(Pageable pageable) {
        PageRequest latestPostPageRequest = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            Sort.by(Sort.Order.desc("postDate"))
        );

        return postRepository.findAll(latestPostPageRequest).map((item) -> (
            GetLatestPostsResponse.builder()
                .postId(item.getPostId())
                .postThumbnail(item.getPostThumbnail())
                .postTitle(item.getPostTitle())
                .postAuthor(item.getPostAuthor().getUserNickname())
                .postAddress(item.getPostAddress())
                .postPrice(item.getPostPrice())
                .build()
        ));
    }

    public Page<GetLatestPostsResponse> searchLatestPosts(Pageable pageable, String keyword) {
        PageRequest latestPostPageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Order.desc("postDate"))
        );

        return postRepository.findByPostTitleContaining(latestPostPageRequest, keyword).map((item) -> (
            GetLatestPostsResponse.builder()
                .postId(item.getPostId())
                .postThumbnail(item.getPostThumbnail())
                .postTitle(item.getPostTitle())
                .postAuthor(item.getPostAuthor().getUserNickname())
                .postAddress(item.getPostAddress())
                .postPrice(item.getPostPrice())
                .build()
        ));
    }

    @Transactional
    public void deletePost(String token, int postId) {
        User user = userService.getInfoByTokenForServer(token);
        Post post = getPost(postId);

        if(user == post.getPostAuthor()) {
            postRepository.deleteById(postId);
        } else {
            throw new AuthorMismatchException(post.getPostAuthor().getUserNickname(), user.getUserNickname());
        }

        return;
    }
}
