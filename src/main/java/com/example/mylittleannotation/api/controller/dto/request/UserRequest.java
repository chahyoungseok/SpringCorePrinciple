package com.example.mylittleannotation.api.controller.dto.request;

import com.example.mylittleannotation.domain.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequest {

    private String name;

    private String id;

    private String password;

    private String description;

    public void setProfanityFilter(String name, String description){
        this.name = name;
        this.description = description;
    }
}
