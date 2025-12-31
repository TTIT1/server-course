package com.example.springjpa.exception;

import java.lang.reflect.InaccessibleObjectException;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.springjpa.dto.response.ApiResponse;

import jakarta.validation.ConstraintViolation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class GlobalExceptionHandler {


    private static final String MIN =  "min";
    private static final String MAX = "max";
    @ExceptionHandler(value = AppExcepotion.class)
    public ResponseEntity<ApiResponse> exceptionRuntime(AppExcepotion apiResponse) {

        ErrorCode errorCode = apiResponse.getErrorCode();
        ApiResponse<Object> objectApiResponse = new ApiResponse<>();
        objectApiResponse.setMessages("Error caused by the user :" + apiResponse.getMessage());
        objectApiResponse.setCode(errorCode.getCode());
        objectApiResponse.setHttpStatusCode(errorCode.getHttpStatusCode());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(objectApiResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> exception(RuntimeException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
           String enumkey = ex.getMessage().toString();
           log.info("test lôi reuntime  ++++++++++++++++++++++++++++++++++++++++++++++++++++ " +enumkey);
        ApiResponse<Object> response = new ApiResponse<>();
        response.setMessages("Error by server: " + ex.getMessage());
        response.setCode(ErrorCode.INTERNAL_ERROR.getCode());
        response.setHttpStatusCode(ErrorCode.INTERNAL_ERROR.getHttpStatusCode());
        return ResponseEntity.status(ErrorCode.INTERNAL_ERROR.getHttpStatusCode()).body(response);
    }
// bbawtsloiox từ annotatin
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleAccessDenied(MethodArgumentNotValidException exception) {
             String enumkey = exception.getFieldError().getDefaultMessage();
             ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
             Map<String,Object> mapAutribute1 = null;
             try {
                        errorCode =ErrorCode.valueOf(enumkey);
                var autribute = exception.getBindingResult().getAllErrors().getFirst().unwrap(ConstraintViolation.class);
                   mapAutribute1 = autribute.getConstraintDescriptor().getAttributes();
              
             } catch (InaccessibleObjectException e) {
             }
             ApiResponse <Object> apiResponse = new ApiResponse<>();
                apiResponse.setMessages("Error by USER : "+ (Objects.nonNull(mapAutribute1)?
                              mapAttribute(errorCode.getMessage(),mapAutribute1):
                              errorCode.getMessage()));
            
            apiResponse.setCode(errorCode.getCode());
   
              return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
    }


    public String mapAttribute(String message, Map<String, Object> attributes) {
        String valid = String.valueOf(attributes.get(MIN));
        return  message.replace("{"+MIN+"}", valid);

    }


}
  