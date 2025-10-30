package com.example.springjpa.exception;

import com.example.springjpa.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GlobalExceptionHandler {


    @ExceptionHandler(value = AppExcepotion.class)
    public ResponseEntity<ApiResponse> exceptionRuntime ( AppExcepotion apiResponse){
        ErrorCode errorCode = apiResponse.getErrorCode();
          ApiResponse<Object> objectApiResponse = new ApiResponse<>();
          objectApiResponse.setMessages("Error caused by the user :"+apiResponse.getMessage());
        objectApiResponse.setCode(errorCode.getCode());
          return ResponseEntity.status(errorCode.getHttpStatusCode()).body(objectApiResponse);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> exception(AppExcepotion appExcepotion){
        ErrorCode errorCode = appExcepotion.getErrorCode();
        ApiResponse<Object> objectApiResponse = new ApiResponse<>();
        objectApiResponse.setMessages("Error by server :"+appExcepotion.getMessage());
        objectApiResponse.setCode(errorCode.getCode());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(objectApiResponse);
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResponse> apiResponseResponseEntity(AccessDeniedException exception){
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiResponse<Object> objectApiResponse = new ApiResponse<>();
        objectApiResponse.setMessages("Error by server :"+errorCode.getMessage());
        objectApiResponse.setCode(errorCode.getCode());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(objectApiResponse);
    }

}
