package com.dsm.shaworld.common.user.service;

import com.dsm.shaworld.common.user.dto.SignInRequest;
import com.dsm.shaworld.common.user.dto.SignInResponse;
import com.dsm.shaworld.common.user.dto.SignUpRequest;
import com.dsm.shaworld.common.user.dto.UserInfoResponse;
import com.dsm.shaworld.common.user.entity.User;
import com.dsm.shaworld.common.user.repository.UserRepository;
import com.dsm.shaworld.global.authorization.JwtProvider;
import com.dsm.shaworld.global.exception.EmailDuplicateException;
import com.dsm.shaworld.global.exception.NicknameDuplicateException;
import com.dsm.shaworld.global.exception.PasswordMismatchException;
import com.dsm.shaworld.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public UserInfoResponse getInfoByToken(String token) {
        User user = getInfoByTokenForServer(token);
        return UserInfoResponse.builder()
                .userEmail(user.getUserEmail())
                .userNickname(user.getUserNickname())
                .userProfile(user.getUserProfile())
                .build();
    }

    public User getInfoByTokenForServer(String token) {
        String email = getEmailFromToken(token);
        return getInfoByUserEmail(email);
    }

    public User getInfoByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail).orElseThrow(() -> new UserNotFoundException(userEmail));
    }

    public SignInResponse signIn(SignInRequest request) {
        User user = getInfoByUserEmail(request.getEmail());
        if(!request.getPassword().equals(user.getUserPassword()))
            throw new PasswordMismatchException(request.getPassword());
        return SignInResponse.builder()
            .token(jwtProvider.generateAccessToken(request.getEmail()))
            .build();
    }

    public boolean emailDuplicationCheck(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    public boolean nicknameDuplicationCheck(String userNickname) {
        return userRepository.existsByUserNickname(userNickname);
    }

    public void signUp(SignUpRequest request) {
        if(!request.getPassword().equals(request.getPasswordConfirm()))
            throw new PasswordMismatchException(request.getPassword(), request.getPasswordConfirm());
        if(emailDuplicationCheck(request.getEmail()))
            throw new EmailDuplicateException(request.getEmail());
        if(nicknameDuplicationCheck(request.getNickname()))
            throw new NicknameDuplicateException(request.getNickname());

        userRepository.save(
            User.builder()
            .userEmail(request.getEmail())
            .userNickname(request.getNickname())
            .userPassword(request.getPassword())
            .build()
        );
    }

    public String getEmailFromToken(String token) {
        String resolvedToken = jwtProvider.resolveToken(token);
        return jwtProvider.validateToken(resolvedToken);
    }

    public void deleteUser(String token) {
        String email = getEmailFromToken(token);
        userRepository.delete(getInfoByUserEmail(email));
    }
}
