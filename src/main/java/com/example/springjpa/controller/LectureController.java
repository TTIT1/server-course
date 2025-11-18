package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;

import com.example.springjpa.dto.resquest.LectureDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.LectureService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LectureController {
    LectureService lectureService;

    // CREATE – lesson.create
    @PreAuthorize("hasAuthority('lesson.create') or hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<LectureDTO>> creatLecture(@RequestBody LectureDTO lectureDTO) {
        ApiResponse<LectureDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(lectureService.addLecture(lectureDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    // Get all – lesson.view_any hoặc user xem free/purchased
    @PreAuthorize("hasAnyAuthority('lesson.view_any','lesson.view_free','lesson.view_purchased','course.view_all') or hasRole('ADMIN')")
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<LectureDTO>>> getAllLecture() {
        ApiResponse<List<LectureDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(lectureService.getAllLecture());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Get by id
    @PreAuthorize("hasAnyAuthority('lesson.view_any','lesson.view_free','lesson.view_purchased','course.view_all') or hasRole('ADMIN')")
    @GetMapping("/get/by/{id}")
    public ResponseEntity<ApiResponse<LectureDTO>> getAllLecture(@PathVariable String id) {
        ApiResponse<LectureDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(lectureService.getbyId(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Update – lesson.update_own / update_any
    @PreAuthorize("hasAnyAuthority('lesson.update_own','lesson.update_any') or hasRole('ADMIN')")
    @PutMapping("/update/by/{id}")
    public ResponseEntity<ApiResponse<LectureDTO>> update(@PathVariable String id, @RequestBody LectureDTO LectureDTO) {
        ApiResponse<LectureDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(lectureService.update(id, LectureDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Delete – lesson.delete_own / delete_any
    @PreAuthorize("hasAnyAuthority('lesson.delete_own','lesson.delete_any') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> aBoolean(@PathVariable String id) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(lectureService.delete(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
