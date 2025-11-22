package com.example.springjpa.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter

public enum ErrorCode {


    UNAUTHORIZED(1001, "Not logged in or token expired", HttpStatus.UNAUTHORIZED),
    FORBIDDEN(1002, "Access denied", HttpStatus.FORBIDDEN),
    INVALID_CREDENTIALS(1003, "Invalid username or password > {min}", HttpStatus.UNAUTHORIZED),
    INVALID_PASSWORD(1004, "Invalid  or password {min}", HttpStatus.UNAUTHORIZED),
    TWOBAD(1111,"Password is not  correct", HttpStatus.UNAUTHORIZED),
    INVALID_KEY(1010, "Uncategorized error", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1112, "Invalid username {min}", HttpStatus.UNAUTHORIZED),
    INVALID_INPUT(2001, "Invalid input data", HttpStatus.BAD_REQUEST),
    MISSING_FIELD(2002, "Missing required field", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(2003, "Email already exists", HttpStatus.CONFLICT),


    USER_EXISTS(3001, "User already exists", HttpStatus.CONFLICT),
    USER_NOT_FOUND(3002, "User not found", HttpStatus.NOT_FOUND),
    DUPLICATE_RECORD(3003, "Duplicate record", HttpStatus.CONFLICT),


    INTERNAL_ERROR(4001, "Internal server error {min}", HttpStatus.INTERNAL_SERVER_ERROR),
    DATABASE_ERROR(4002, "Database connection error", HttpStatus.INTERNAL_SERVER_ERROR),
    SERVICE_UNAVAILABLE(4003, "Service temporarily unavailable", HttpStatus.SERVICE_UNAVAILABLE),


    BAD_REQUEST(5001, "Bad request", HttpStatus.BAD_REQUEST),
    NOT_FOUND(5002, "Resource not found", HttpStatus.NOT_FOUND),
    TIMEOUT(5003, "Request timeout", HttpStatus.REQUEST_TIMEOUT),


    SUCCESS(1000, "Success", HttpStatus.OK),
    REGISTER_SUCCESS(10, "Registration successful", HttpStatus.CREATED),

    INVALID_AGE(9999,"You are not old enough to learn this  {min}",HttpStatus.UNAUTHORIZED);

    final int code;
    final String message;
    final HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
