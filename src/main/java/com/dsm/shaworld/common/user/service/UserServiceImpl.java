package com.dsm.shaworld.common.user.service;

import com.dsm.shaworld.common.user.dto.SignUpRequest;
import com.dsm.shaworld.common.user.entity.User;
import com.dsm.shaworld.common.user.repository.UserRepository;
import com.dsm.shaworld.global.exception.EmailDuplicateException;
import com.dsm.shaworld.global.exception.NicknameDuplicationException;
import com.dsm.shaworld.global.exception.PasswordMismatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean emailDuplicationCheck(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    @Override
    public boolean nicknameDuplicationCheck(String userNickname) {
        return userRepository.existsByUserNickname(userNickname);
    }

    @Override
    public void signUp(SignUpRequest request) {
        if(!request.getPassword().equals(request.getPasswordConfirm()))
            throw new PasswordMismatchException(request.getPassword(), request.getPasswordConfirm());
        if(emailDuplicationCheck(request.getEmail()))
            throw new EmailDuplicateException(request.getEmail());
        if(nicknameDuplicationCheck(request.getNickname()))
            throw new NicknameDuplicationException(request.getNickname());

        userRepository.save(
            User.builder()
            .userEmail(request.getEmail())
            .userNickname(request.getNickname())
            .userPassword(request.getPassword())
            .build()
        );
    }
}
