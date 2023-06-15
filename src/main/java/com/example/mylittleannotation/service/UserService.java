package com.example.mylittleannotation.service;

import com.example.mylittleannotation.aop.annotation.ProfanityFilter;
import com.example.mylittleannotation.api.controller.dto.request.LoginRequest;
import com.example.mylittleannotation.api.controller.dto.request.UserRequest;
import com.example.mylittleannotation.api.controller.dto.response.UserResponse;
import com.example.mylittleannotation.domain.entity.Role;
import com.example.mylittleannotation.domain.entity.User;
import com.example.mylittleannotation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void readUser(Long id) {
        userRepository.findById(id);
    }

    public void readRegister() {

    }

    public void readAdmin() {

    }

    @ProfanityFilter
    public UserResponse create(UserRequest request) {
        User user = userRepository.save(
                User.builder().userRequest(request).build());

        return new UserResponse(user.getName(), user.getDescription());
    }


    public Role login(LoginRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("LoginRequest 가 없습니다.");
        }

        Role role = null;

        User user = userRepository.findUserById(request.getId());
        if (user.getPassword().equals(request.getPassword())) {
            return user.getRole();
        }
        else {
            throw new IllegalArgumentException("로그인에 실패하였습니다.");
        }
    }
}
