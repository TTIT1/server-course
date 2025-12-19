package com.example.springjpa.service;



import java.util.List;

import com.example.springjpa.dto.resquest.VideoDTO;


public interface VideoService {
    List<VideoDTO> findAll();
    void deleteById(String id);
    VideoDTO updateById(String id, VideoDTO videoDTO);
    VideoDTO save(VideoDTO videoDTO);
    VideoDTO findById(String integer);
}
