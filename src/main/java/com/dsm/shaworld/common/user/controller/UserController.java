package com.dsm.shaworld.common.user.controller;

import com.dsm.shaworld.common.user.dto.SignUpRequest;
import com.dsm.shaworld.common.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signUp")
    public void signUp(@RequestBody SignUpRequest request) {
        userService.signUp(request);
    }
}