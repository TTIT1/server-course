package com.example.springjpa.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;



@Configurable
public class CloudinaryConfig {

   @Value("${CLOUDINARY_CLOUD_NAME}")
private String cloudName;

@Value("${CLOUDINARY_API_KEY}")
private String apiKey;

@Value("${CLOUDINARY_API_SECRET}")
private String apiSecret;

@Value("${CLOUDINARY_SECURE:true}")
private boolean secure;

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        config.put("secure", String.valueOf(secure));
        return new Cloudinary(config);
    }
}
