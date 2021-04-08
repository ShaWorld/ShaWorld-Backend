package com.dsm.shaworld.domain.user.service;

import com.dsm.shaworld.domain.user.dto.*;
import com.dsm.shaworld.domain.user.entity.User;
import com.dsm.shaworld.domain.user.repository.UserRepository;
import com.dsm.shaworld.global.authorization.JwtProvider;
import com.dsm.shaworld.global.exception.*;
import com.dsm.shaworld.global.s3service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final S3Service s3Service;

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

    @Transactional
    public void deleteUser(String token) {
        String email = getEmailFromToken(token);
        userRepository.delete(getInfoByUserEmail(email));
    }

    @Transactional
    public void changeNickname(String token, NicknameChangeRequest request) {
        User user = getInfoByTokenForServer(token);

        if(nicknameDuplicationCheck(request.getChangedNickname()))
            throw new NicknameDuplicateException(request.getChangedNickname());

        user.setUserNickname(request.getChangedNickname());
        return;
    }

    @Transactional
    public void changePassword(String token, PasswordChangeRequest request) {
        User user = getInfoByTokenForServer(token);

        if(!request.getCurrentPassword().equals(user.getUserPassword()))
            throw new PasswordMismatchException(request.getCurrentPassword());
        if(!request.getChangePassword().equals(request.getChangePasswordConfirm()))
            throw new PasswordMismatchException(request.getChangePassword(), request.getChangePasswordConfirm());

        user.setUserPassword(request.getChangePassword());
        return;
    }

    @Transactional
    public void changeProfile(String token, MultipartFile file) throws IOException {
        User user = getInfoByTokenForServer(token);

        String imgPath = s3Service.uploadS3File(file);
        user.setUserProfile(imgPath);

        return;
    }
}