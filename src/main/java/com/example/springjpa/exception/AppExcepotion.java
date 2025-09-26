package com.example.springjpa.exception;

public class AppException extends  RuntimeException{
    public AppException( ErrorCode errorCode) {
        super(errorCode.getMessages());
        this.errorCode = errorCode;
    }

    private  ErrorCode errorCode;
}
