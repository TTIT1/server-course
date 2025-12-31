package com.example.springjpa.service.Impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.example.springjpa.service.CloudinaryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    
   private Cloudinary  cloudinary;
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().uploadLarge(
                file.getInputStream(),
                Map.of("resource_type", "video",
                        "folder", "course_videos"
                )
            );
            String videoUrl = uploadResult.get("secure_url").toString();
            
            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            e.printStackTrace();
                

        return null;
    }
  
}
}
