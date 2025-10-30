package com.example.springjpa.service;



import com.example.springjpa.dto.resquest.VideoDTO;

import java.util.List;


public interface VideoService {
    List<VideoDTO> findAll();
    void deleteById(String id);
    VideoDTO updateById(String id, VideoDTO videoDTO);
    VideoDTO save(VideoDTO videoDTO);
    VideoDTO findById(String integer);
}
