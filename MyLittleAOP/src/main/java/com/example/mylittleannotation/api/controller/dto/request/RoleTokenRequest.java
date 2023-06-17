package com.example.mylittleannotation.api.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoleTokenRequest {

    private String roleToken;

    public void setDecoding(String roleToken) {
        this.roleToken = roleToken;
    }
}
