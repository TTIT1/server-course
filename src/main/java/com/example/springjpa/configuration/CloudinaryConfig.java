package com.example.springjpa.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;



@Configurable
public class CloudinaryConfig {
@Value("${CLOUDINARY_NAME}")
private String cloudName;

@Value("${cloudinary.api-key}")
private String apiKey;

@Value("${cloudinary.api-secret}")
private String apiSecret;

@Value("${cloudinary.secure}")
private boolean cloudSecure;



    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        config.put("secure", String.valueOf(cloudSecure));
        return new Cloudinary(config);
    }
}
