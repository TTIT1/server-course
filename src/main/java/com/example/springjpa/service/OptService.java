package com.example.springjpa.service;


public interface OptService {
    String generateOTP(String email, int minutesValid);
    public boolean verifyOTP(String email, String otp);

}
