package com.example.springjpa.service;

import org.springframework.web.multipart.MultipartFile;

public interface  CloudinaryService {
    String uploadVideo(MultipartFile file);
}
