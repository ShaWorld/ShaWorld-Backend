package com.dsm.shaworld.domain.post.repository;

import com.dsm.shaworld.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
