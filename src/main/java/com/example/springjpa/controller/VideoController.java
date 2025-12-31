package com.example.springjpa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.resquest.VideoDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.course.Video;
import com.example.springjpa.service.Impl.CloudinaryServiceImpl;
import com.example.springjpa.service.VideoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class VideoController {

    VideoService videoService;
    CloudinaryServiceImpl cloudinaryServiceImpl;

    // Xem danh sách video – bất kỳ ai có quyền xem bài học/khóa học
    @PreAuthorize("hasAnyAuthority('lesson.view_any','course.view_all','course.view_free','course.view_purchased') or hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<VideoDTO>>> getAllVideos() {
        ApiResponse<List<VideoDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(videoService.findAll());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }

    // Xem chi tiết video
    @PreAuthorize("hasAnyAuthority('lesson.view_any','course.view_all','course.view_free','course.view_purchased') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VideoDTO>> getVideoById(@PathVariable String id) {
        ApiResponse<VideoDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(videoService.findById(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }

    // Tạo video – tác giả/quản trị có quyền material.upload
    @PreAuthorize("hasAuthority('material.upload') or hasRole('ADMIN')")
    //@PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PostMapping("/upload/video")
    public ResponseEntity<ApiResponse<VideoDTO>> saveVideo(

        @RequestBody  VideoDTO videoDTO   
    ) {
                
         
            
                 ApiResponse<VideoDTO> apiResponse = new ApiResponse<>();
                    apiResponse.setRsulte(videoService.save(videoDTO));
                    apiResponse.setCode(ErrorCode.SUCCESS.getCode());
                    apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
                 return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
          
       
    }

    // Cập nhật video – quyền material.update
    @PreAuthorize("hasAuthority('material.update') or hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<VideoDTO>> updateVideo(@PathVariable String id, @RequestBody VideoDTO videoDTO) {
        ApiResponse<VideoDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(videoService.updateById(id, videoDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }

    // Xóa video – quyền material.delete
    @PreAuthorize("hasAuthority('material.delete') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVideo(@PathVariable String id) {
        videoService.deleteById(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }
}
