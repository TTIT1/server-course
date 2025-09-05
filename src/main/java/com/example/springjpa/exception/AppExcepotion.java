package com.example.springjpa.exception;

public class AppExcepotion extends RuntimeException{
    public AppExcepotion( ErrorCode errorCode) {
        super(errorCode.getMessages());
        this.errorCode = errorCode;
    }

    private  ErrorCode errorCode;
}
