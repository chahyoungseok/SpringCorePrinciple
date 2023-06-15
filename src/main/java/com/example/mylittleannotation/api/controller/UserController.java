package com.example.mylittleannotation.api.controller;

import com.example.mylittleannotation.api.controller.dto.request.LoginRequest;
import com.example.mylittleannotation.api.controller.dto.request.UserRequest;
import com.example.mylittleannotation.api.controller.dto.response.UserResponse;
import com.example.mylittleannotation.domain.entity.Role;
import com.example.mylittleannotation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<Role> login(@RequestBody LoginRequest request) {
        Role result = userService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        UserResponse result = userService.create(request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
