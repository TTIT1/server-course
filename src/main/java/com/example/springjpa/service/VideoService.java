package com.example.springjpa.service;

import com.example.springjpa.dto.VideoDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.model.Video;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public interface VideoService {
    List<VideoDTO> findAll();
    void deleteById(Integer id);
    VideoDTO updateById(int id, VideoDTO videoDTO);
    VideoDTO save(VideoDTO videoDTO);
    VideoDTO findById(Integer integer);
}
