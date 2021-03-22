package com.dsm.shaworld.common.user.repository;

import com.dsm.shaworld.common.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserNickname(String userNickname);
}