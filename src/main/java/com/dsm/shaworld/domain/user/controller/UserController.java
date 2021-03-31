package com.dsm.shaworld.domain.user.controller;

import com.dsm.shaworld.domain.user.dto.*;
import com.dsm.shaworld.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public UserInfoResponse getUserInfoByUserEmail(@RequestHeader(value="Authorization") String token) {
        return userService.getInfoByToken(token);
    }

    @PostMapping("/signin")
    @ResponseStatus(value = HttpStatus.OK)
    public SignInResponse signIn(@RequestBody @Valid SignInRequest request) { return userService.signIn(request); }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody @Valid SignUpRequest request) {
        userService.signUp(request);
    }

    @DeleteMapping("/deleteuser")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@RequestHeader(value="Authorization") String token) { userService.deleteUser(token); }

    @PatchMapping("/nickname")
    @ResponseStatus(value = HttpStatus.OK)
    public void changeInfo(@RequestHeader(value="Authorization") String token, @RequestBody @Valid NicknameChangeRequest request) {
        userService.changeNickname(token, request);
    }

    @PatchMapping("/password")
    @ResponseStatus(value = HttpStatus.OK)
    public void changePassword(@RequestHeader(value="Authorization") String token, @RequestBody @Valid PasswordChangeRequest request) {
        userService.changePassword(token, request);
    }

    @PatchMapping("/profile")
    @ResponseStatus(value = HttpStatus.OK)
    public void changeProfile(@RequestHeader(value="Authorization") String token, @RequestBody @Valid ProfileChangeRequest request) {
        userService.changeProfile(token, request);
    }

}