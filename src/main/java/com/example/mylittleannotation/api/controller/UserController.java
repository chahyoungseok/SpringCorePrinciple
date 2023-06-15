package com.example.mylittleannotation.api.controller;

import com.example.mylittleannotation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        userService.read(id);
    }

}
