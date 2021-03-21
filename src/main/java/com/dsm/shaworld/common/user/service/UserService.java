package com.dsm.shaworld.common.user.service;

import com.dsm.shaworld.common.user.dto.SignUpRequest;
import com.dsm.shaworld.common.user.repository.UserRepository;
import com.dsm.shaworld.global.exception.PasswordMismatchException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository authRepository;

    public UserService(UserRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void signUp(SignUpRequest request) {
        if(request.getPassword() != request.getPasswordConfirm())
            throw new PasswordMismatchException(request.getPassword(), request.getPasswordConfirm());
    }
}
