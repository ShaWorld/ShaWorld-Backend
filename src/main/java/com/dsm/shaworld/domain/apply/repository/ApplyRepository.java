package com.dsm.shaworld.domain.apply.repository;

import com.dsm.shaworld.domain.apply.entity.Apply;
import com.dsm.shaworld.domain.post.entity.Post;
import com.dsm.shaworld.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply, Integer> {
    Apply findByApplyId(int applyId);
    List<Apply> findByApplyApplicant(User user);
    List<Apply> findByApplyPostPostAuthor(User user);
    boolean existsByApplyPostAndApplyApplicant(Post post, User user);
}
