package com.dsm.shaworld.common.user.service;

import com.dsm.shaworld.common.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository authRepository;

    public UserService(UserRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void signUp() {

    }
}
