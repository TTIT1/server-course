package com.example.springjpa.exception;

import com.example.springjpa.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse> exceptionRuntime ( RuntimeException runtimeException){
          ApiResponse<Object> objectApiResponse = new ApiResponse<>();
          objectApiResponse.setMessages("Error caused by the user :"+runtimeException.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(objectApiResponse);
    }
    @ExceptionHandler
    public ResponseEntity<ApiResponse> exception(Exception exception){
        ApiResponse<Object> objectApiResponse = new ApiResponse<>();
        objectApiResponse.setMessages("Error by server :"+exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(objectApiResponse);
    }

}
