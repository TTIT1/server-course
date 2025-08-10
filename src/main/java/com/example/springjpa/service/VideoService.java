package com.example.springjpa.service;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.VideoDTO;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.model.Video;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoService {
    @Autowired
     VideoRepository videoRepository;
    @Autowired
    LectureRepository lectureRepository;
    public VideoDTO toModel(Video video){
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setLectureid(video.getId());
        videoDTO.setName(video.getName());
        videoDTO.setSize(video.getSize());
        videoDTO.setUrl(video.getUrl());
        videoDTO.setLength(video.getLength());
        videoDTO.setId(video.getId());
        return videoDTO;

    }

    public List<VideoDTO> findAll(){
        return videoRepository.findAll().stream().map(this::toModel).collect(Collectors.toList());
    }
    public VideoDTO findById(Integer id){
       Video videoDTO = videoRepository.findById(id)
               .orElseThrow(()->new notification("i don't find"));
       return this.toModel(videoDTO);

    }
    public void deleteById(Integer id){
        Video video =  videoRepository.findById(id).orElseThrow(()->new notification("i don't find"));
         videoRepository.delete(video);
    }
    public VideoDTO updateById(int id, VideoDTO videoDTO){
        if(id != videoDTO.getId()){
            throw  new notification("bug data");
        }
        else
        {
            Video video =  videoRepository.findById(videoDTO.getId()).orElseThrow(()->new notification("i don't find"));
            video.setLength(videoDTO.getLength());
            return this.toModel(videoRepository.save(video));
        }

    }
    public VideoDTO save(VideoDTO videoDTO){
        Lecture lecture = lectureRepository.findById(videoDTO.getLectureid()).orElseThrow(() ->
                new RuntimeException("I don't find data lecture id = " + videoDTO.getLectureid()));
        try {
            Video video = new Video();
            video.setName(videoDTO.getName());
            video.setSize(videoDTO.getSize());
            video.setUrl(videoDTO.getUrl());
            video.setLength(videoDTO.getLength());
            video.setLecture(lecture);
            videoRepository.save(video);
        }catch (Exception e){
            throw  new notification("bug data");
        }
        return videoDTO;
    }
}
