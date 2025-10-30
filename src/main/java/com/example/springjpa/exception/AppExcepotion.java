package com.example.springjpa.exception;

import lombok.Getter;

@Getter
public class AppExcepotion extends RuntimeException {
  public AppExcepotion(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  private ErrorCode errorCode;
}
