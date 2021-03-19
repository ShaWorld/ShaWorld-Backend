package com.dsm.shaworld.common.auth.repository;


import com.dsm.shaworld.common.auth.domain.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Integer> {
}