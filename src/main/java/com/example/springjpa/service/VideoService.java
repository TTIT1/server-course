package com.example.springjpa.service;



import com.example.springjpa.dto.resquest.VideoDTO;

import java.util.List;


public interface VideoService {
    List<VideoDTO> findAll();
    void deleteById(Integer id);
    VideoDTO updateById(int id, VideoDTO videoDTO);
    VideoDTO save(VideoDTO videoDTO);
    VideoDTO findById(Integer integer);
}
