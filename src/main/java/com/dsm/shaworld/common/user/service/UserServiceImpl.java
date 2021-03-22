package com.dsm.shaworld.common.user.service;

import com.dsm.shaworld.common.user.dto.SignUpRequest;
import com.dsm.shaworld.common.user.repository.UserRepository;
import com.dsm.shaworld.global.authorization.JwtProvider;
import com.dsm.shaworld.global.exception.PasswordMismatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void signUp(SignUpRequest request) {
        if(!request.getPassword().equals(request.getPasswordConfirm()))
            throw new PasswordMismatchException(request.getPassword(), request.getPasswordConfirm());

        userRepository.save(
                
        )
    }
}
