package com.example.springjpa.exception;

import com.example.springjpa.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
          ApiResponse<Object> objectApiResponse = new ApiResponse<>();
                  objectApiResponse.setMessages("Lỗi do custom: "+ex.getMessage());
          return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(objectApiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Lỗi hệ thống: " + ex.getMessage());
    }
}
