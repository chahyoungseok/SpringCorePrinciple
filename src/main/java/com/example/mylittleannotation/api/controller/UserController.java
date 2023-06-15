package com.example.mylittleannotation.api.controller;

import com.example.mylittleannotation.api.controller.dto.UserRequest;
import com.example.mylittleannotation.api.controller.dto.response.UserResponse;
import com.example.mylittleannotation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/open")
    public String success(){
        return "success";
    }

    @GetMapping("/user")
    public void read(@RequestParam("id") Long id) {
        userService.readUser(id);
    }


    @PostMapping("/user")
    public UserResponse create(@RequestBody UserRequest request) {
        return userService.create(request);
    }
}
