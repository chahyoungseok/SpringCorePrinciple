package com.example.mylittleannotation.api.controller;

import com.example.mylittleannotation.api.controller.aes.AES;
import com.example.mylittleannotation.api.controller.aes.AESProperty;
import com.example.mylittleannotation.api.controller.dto.request.LoginRequest;
import com.example.mylittleannotation.api.controller.dto.request.RoleTokenRequest;
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
    private final AES aes;

    @GetMapping("/open")
    public String success(){
        return "success";
    }

    @PostMapping("/user_roletoken")
    public ResponseEntity<String > read(@RequestBody RoleTokenRequest roleToken) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.readUser(roleToken));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Role result = userService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(aes.encrypt(result.toString(), AESProperty.securityKey));
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        UserResponse result = userService.create(request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
