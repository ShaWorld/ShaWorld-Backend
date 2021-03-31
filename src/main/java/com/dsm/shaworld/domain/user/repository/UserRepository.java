package com.dsm.shaworld.domain.user.repository;

import com.dsm.shaworld.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserEmail(String userEmail);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserNickname(String userNickname);
}