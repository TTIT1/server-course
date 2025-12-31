package com.example.springjpa.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendOtpEmail(String to, String otp)throws MessagingException;
}
