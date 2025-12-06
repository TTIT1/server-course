package com.example.springjpa.controller;


import com.example.springjpa.dto.response.ApiResponse;

import com.example.springjpa.dto.resquest.CourseDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.course.Course;
import com.example.springjpa.service.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v2")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    CourseService courseService;

    // Tạo khóa học – AUTHOR hoặc ADMIN có quyền course.create
    @PreAuthorize("hasAuthority('course.create') or hasRole('ADMIN')")
    @PostMapping("/courses")
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseDTO courseDTO) {
        ApiResponse<Course> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.save(courseDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    // Xem chi tiết khóa học – ADMIN hoặc ai có quyền view_all / lesson.view_any
    @PreAuthorize("hasAnyAuthority('course.view_all','lesson.view_any','course.view_free','course.view_purchased') or hasRole('ADMIN')")
    @GetMapping("/get/by/{id}")
    public ResponseEntity<ApiResponse<Course>> getcoursebyid(@PathVariable String id) {
        ApiResponse<Course> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.GetByID(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Cập nhật khóa học – người có quyền update_own hoặc update_any
    @PreAuthorize("hasAnyAuthority('course.update_own','course.update_any') or hasRole('ADMIN')")
    @PutMapping("/Update/By/{id}")
    public ResponseEntity<ApiResponse<Course>> updatebyid(@PathVariable String id, @RequestBody CourseDTO courseDTO) {
        ApiResponse<Course> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.update(id, courseDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Lấy tất cả khóa học – quyền xem tất cả hoặc xem free/đã mua
    @PreAuthorize("hasAnyAuthority('course.view_all','course.view_free','course.view_purchased') or hasRole('ADMIN')")
    @GetMapping("/Get/All/Course")
    public ResponseEntity<ApiResponse<List<CourseDTO>>> getallcourse() {
        ApiResponse<List<CourseDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.getCourse());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    // Xóa khóa học – ADMIN hoặc người có quyền delete_any/delete_own
    @PreAuthorize("hasAnyAuthority('course.delete_any','course.delete_own') or hasRole('ADMIN')")
    @DeleteMapping("/Delete/By/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deletebyid(@PathVariable String id) {
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.DeleteById(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
