package com.example.springjpa.controller;


import com.example.springjpa.dto.response.ApiResponse;

import com.example.springjpa.dto.resquest.CourseDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.course.Course;
import com.example.springjpa.service.CourseService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v2")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CourseController {

    CourseService courseService;
    @PreAuthorize("has")
    @PostMapping("/All/New/Course")
    public ResponseEntity<ApiResponse<Course>> AllNewCourse(@RequestBody CourseDTO courseDTO){
          ApiResponse<Course> apiResponse = new ApiResponse<>();
          apiResponse.setRsulte(courseService.save(courseDTO));
          apiResponse.setCode(ErrorCode.SUCCESS.getCode());
          apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
          return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/Get/By/{id}")
    public  ResponseEntity<ApiResponse<Course>> GetById(@PathVariable String id){
           ApiResponse<Course> apiResponse = new ApiResponse<>();
           apiResponse.setRsulte(courseService.GetByID(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/Update/By/{id}")
    public  ResponseEntity<ApiResponse<Course>> UpdateByID(@PathVariable String id ,@RequestBody CourseDTO courseDTO){
        ApiResponse<Course> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.update(id, courseDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/Get/All/Course")
    public ResponseEntity<ApiResponse<List<CourseDTO>>> getAllCourse(){
        ApiResponse<List<CourseDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.getCourse());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/Delete/By/{id}")
    public  ResponseEntity<ApiResponse<Boolean>> DeleteById(@PathVariable String id){
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.DeleteById(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
