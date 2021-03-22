package com.dsm.shaworld.common.user.service;

import com.dsm.shaworld.common.user.dto.SignUpRequest;

public interface UserService {
    boolean emailDuplicationCheck(String userEmail);
    boolean nicknameDuplicationCheck(String userNickname);
    void signUp(SignUpRequest request);
}
