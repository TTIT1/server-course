package com.example.springjpa.service.Impl;

import com.example.springjpa.service.EmailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${gmail.gmail_key}")
    String gmail;
     @Value("${gmail.gmail_app_password}")
     String password;
  
    public void sendOtpEmail(String to, String otp) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gmail, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(gmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Mã xác thực của bạn");
        message.setText("Mã OTP của bạn là: " + otp + "\nMã có hiệu lực trong 10 phút.");

        Transport.send(message);
    }
}