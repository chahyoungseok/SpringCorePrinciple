package com.example.mylittleannotation.api.controller;

import com.example.mylittleannotation.api.controller.dto.response.IllegalArgumentExceptionResponse;
import com.example.mylittleannotation.api.controller.dto.response.RuntimeExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentExceptionResponse> IllegalArgumentException(IllegalArgumentException ex){
        IllegalArgumentExceptionResponse exceptionResponse = new IllegalArgumentExceptionResponse();

        exceptionResponse.setExceptionMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RuntimeExceptionResponse> RuntimeException(RuntimeExceptionResponse ex){
        RuntimeExceptionResponse exceptionResponse = new RuntimeExceptionResponse();

        exceptionResponse.setExceptionMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exceptionResponse);
    }
}