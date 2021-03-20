package com.dsm.shaworld.common.user.domain.repository;

import com.dsm.shaworld.common.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}