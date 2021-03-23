package com.dsm.shaworld.common.user.controller;

import com.dsm.shaworld.common.user.dto.SignInRequest;
import com.dsm.shaworld.common.user.dto.SignInResponse;
import com.dsm.shaworld.common.user.dto.SignUpRequest;
import com.dsm.shaworld.common.user.entity.User;
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

    @GetMapping("/getUser")
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserInfoByUserEmail(@RequestParam("email") String email) {
        System.out.println("a");
        return userService.getInfoByUserEmail(email);
    }

    @PostMapping("/signIn")
    @ResponseStatus(value = HttpStatus.OK)
    public SignInResponse signIn(@RequestBody @Valid SignInRequest request) { return userService.signIn(request); }

    @PostMapping("/signUp")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(request);
    }

    @DeleteMapping("/deleteUser")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@RequestHeader(value="authorization") String token) { userService.deleteUser(token); }
}