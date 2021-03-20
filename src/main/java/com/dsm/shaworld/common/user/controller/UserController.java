package com.dsm.shaworld.common.user.controller;

import com.dsm.shaworld.common.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService authService;

    public UserController(UserService authService) {
        this.authService = authService;
    }

    @PostMapping("signUp")
    public void signUp() {

    }
}