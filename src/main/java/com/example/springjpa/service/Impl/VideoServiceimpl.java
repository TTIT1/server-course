package com.example.springjpa.service.Impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.springjpa.dto.resquest.VideoDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.course.Lecture;
import com.example.springjpa.model.course.Video;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.VideoRepository;
import com.example.springjpa.service.VideoService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class VideoServiceimpl implements VideoService {

VideoRepository videoRepository;
 //CloudinaryServiceImpl cloudinaryServiceImpl;

    LectureRepository lectureRepository;
    public VideoDTO toModel(Video video){
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setName(video.getName());
        videoDTO.setSize(video.getSize());
        videoDTO.setLength(video.getLength());
        videoDTO.setId(video.getId());
        return videoDTO;

    }

    public List<VideoDTO> findAll(){
        return videoRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }
    public VideoDTO findById(String id){
        Video videoDTO = videoRepository.findById(id)
                .orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
        return this.toModel(videoDTO);

    }
    public void deleteById(String id){
        Video video =  videoRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
        videoRepository.delete(video);
    }
    public VideoDTO updateById(String id, VideoDTO videoDTO){
        if(id != videoDTO.getId()){
            throw  new AppExcepotion(ErrorCode.INVALID_INPUT);
        }
        else
        {
            Video video =  videoRepository.findById(videoDTO.getId()).orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
            video.setLength(videoDTO.getLength());
            return this.toModel(videoRepository.save(video));
        }

    }
    public VideoDTO save(VideoDTO videoDTO ){
        Lecture lecture = lectureRepository.findById(videoDTO.getLectureid())
                .orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
        List<Video>videos = videoRepository.findAll();
       
        Video video = new Video();
        video.setName(videoDTO.getName());
        video.setSize(videoDTO.getSize());
        video.setLength(videoDTO.getLength());
        video.setUrl(videoDTO.getUrl());
        video.setLecture(lecture) ;
        return toModel(videoRepository.save(video)) ;


    }

   
}
