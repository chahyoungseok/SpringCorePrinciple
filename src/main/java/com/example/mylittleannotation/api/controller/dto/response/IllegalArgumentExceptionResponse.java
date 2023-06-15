package com.example.mylittleannotation.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IllegalArgumentExceptionResponse {
    private String message;

    public void setExceptionMessage(String message){
        this.message = message;
    }
}
