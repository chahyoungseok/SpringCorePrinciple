package com.example.mylittleannotation.service;

import com.example.mylittleannotation.aop.annotation.ProfanityFilter;
import com.example.mylittleannotation.aop.annotation.RoleTokenAuthorization;
import com.example.mylittleannotation.api.controller.dto.request.LoginRequest;
import com.example.mylittleannotation.api.controller.dto.request.RoleTokenRequest;
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

    @RoleTokenAuthorization
    public String readUser(RoleTokenRequest roleToken) {
        if (roleToken.getRoleToken().equals(Role.USER.toString())) {
            return "USER_INFO";
        } else if (roleToken.getRoleToken().equals(Role.REGISTER.toString())) {
            return "REGISTER_INFO";
        } else {
            return "ADMIN_INFO";
        }
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

        User user = userRepository.findUserById(request.getId());
        if (user.getPassword().equals(request.getPassword())) {
            return user.getRole();
        }
        else {
            throw new IllegalArgumentException("로그인에 실패하였습니다.");
        }
    }
}
