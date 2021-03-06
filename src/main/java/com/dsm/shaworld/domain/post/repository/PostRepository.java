package com.dsm.shaworld.domain.post.repository;

import com.dsm.shaworld.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findByPostId(int postId);

    Page<Post> findAll(Pageable pageable);
    Page<Post> findByPostTitleContaining(Pageable pageable, String keyword);
}
