package com.example.springjpa.controller;

import com.example.springjpa.dto.VideoDTO;
import com.example.springjpa.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video/v1")
public class VideoReourceController {
     @Autowired
     VideoService videoService;
    @PostMapping("/add/video")
    public ResponseEntity<?> addvideo(@RequestBody VideoDTO videoDTO){
        try {
            VideoDTO textDTO1 = videoService.save(videoDTO);
            return ResponseEntity.status(HttpStatus.OK).body("ok  :" +textDTO1);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/get/all/video")
    public List<VideoDTO> findAllVideo(){
        return videoService.findAll();
    }
    @GetMapping("/get/find/{id}")
    public ResponseEntity<?> findVideoById(@PathVariable Integer id){
        try {
            VideoDTO videoDTO =   videoService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(videoDTO);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVideo(@PathVariable int id, @RequestBody VideoDTO videoDTO){
        try {
            VideoDTO videoDTO1 =   videoService.updateById( id,videoDTO);
            return ResponseEntity.status(HttpStatus.OK).body(videoDTO1);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
