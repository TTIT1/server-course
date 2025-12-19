package com.example.springjpa.service;



import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.springjpa.dto.resquest.VideoDTO;


public interface VideoService {
    List<VideoDTO> findAll();
    void deleteById(String id);
    VideoDTO updateById(String id, VideoDTO videoDTO);
    VideoDTO save(VideoDTO videoDTO,MultipartFile file);
    VideoDTO findById(String integer);
}
