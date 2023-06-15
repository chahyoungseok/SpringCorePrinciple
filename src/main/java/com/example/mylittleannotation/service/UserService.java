package com.example.mylittleannotation.service;

import com.example.mylittleannotation.aop.annotation.ProfanityFilter;
import com.example.mylittleannotation.api.controller.dto.UserRequest;
import com.example.mylittleannotation.api.controller.dto.response.UserResponse;
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
}
