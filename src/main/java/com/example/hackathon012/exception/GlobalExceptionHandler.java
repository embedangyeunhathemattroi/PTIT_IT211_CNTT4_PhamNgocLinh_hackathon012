package com.example.hackathon012.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String ,String> errors =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName =((FieldError) error).getField();

            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);


        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler (ResourceAccessException.class)
    public ResponseEntity<Map<String,String>> handleNotFoundException(ResourceAccessException ex){
        Map<String,String> errors=new HashMap<>();
        errors.put("error","NOT FOUND");
        errors.put("message",ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);

    }


}
