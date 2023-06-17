package com.example.mylittleannotation.api.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RuntimeExceptionResponse {
    private final Exception exception = new RuntimeException();

    private String message;

    public void setExceptionMessage(String message){
        this.message = message;
    }
}
