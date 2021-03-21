package com.dsm.shaworld.common.user.controller;

import com.dsm.shaworld.common.user.dto.SignUpRequest;
import com.dsm.shaworld.common.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("signUp")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(request);
    }
}