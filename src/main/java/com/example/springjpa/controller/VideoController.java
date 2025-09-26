package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.VideoDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.VideoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/video")

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class VideoController {

      VideoService videoService;


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<VideoDTO>>> getAllVideos() {
        ApiResponse<List<VideoDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(videoService.findAll());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VideoDTO>> getVideoById(@PathVariable Integer id) {
        ApiResponse<VideoDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(videoService.findById(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse<VideoDTO>> saveVideo(@RequestBody VideoDTO videoDTO) {
        ApiResponse<VideoDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(videoService.save(videoDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<VideoDTO>> updateVideo(@PathVariable int id, @RequestBody VideoDTO videoDTO) {
        ApiResponse<VideoDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(videoService.updateById(id, videoDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVideo(@PathVariable Integer id) {
        videoService.deleteById(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }
}
