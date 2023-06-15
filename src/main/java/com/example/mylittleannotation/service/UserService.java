package com.example.mylittleannotation.service;

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
}
