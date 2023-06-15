package com.example.mylittleannotation.api.controller;

import com.example.mylittleannotation.api.controller.dto.response.IllegalArgumentExceptionResponse;
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
}