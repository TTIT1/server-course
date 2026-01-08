package com.example.springjpa.payment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter

public class VNPAYConfig {

  @Value("${payment.vnPay.url}")
private String vnp_PayUrl;

@Value("${payment.vnPay.returnUrl}")
private String vnp_ReturnUrl;

@Value("${payment.vnPay.tmnCode}")
private String vnp_TmnCode;

@Value("${payment.vnPay.secretKey}")
private String secretKey;

@Value("${payment.vnPay.version}")
private String vnp_Version;

@Value("${payment.vnPay.command}")
private String vnp_Command;

@Value("${payment.vnPay.orderType}")
private String orderType;


    public Map<String, String> getBaseParams() {
        Map<String, String> params = new HashMap<>();
        params.put("vnp_Version", vnp_Version);
        params.put("vnp_Command", vnp_Command);
        params.put("vnp_TmnCode", vnp_TmnCode);
        params.put("vnp_CurrCode", "VND");
        params.put("vnp_Locale", "vn");
        params.put("vnp_OrderType", orderType);
        params.put("vnp_ReturnUrl", vnp_ReturnUrl);
        return params;
    }
}
