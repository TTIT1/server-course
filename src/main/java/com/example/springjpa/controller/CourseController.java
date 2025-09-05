package com.example.springjpa.controller;


import com.example.springjpa.dto.ApiResponse;
import com.example.springjpa.dto.CourseDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Course;
import com.example.springjpa.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v2")
@RestController
public class CourseController {
    @Autowired
    CourseService courseService;
    @PostMapping("/All/New/Course")
    public ResponseEntity<ApiResponse<Course>> AllNewCourse(@RequestBody CourseDTO courseDTO){
          ApiResponse<Course> apiResponse = new ApiResponse<>();
          apiResponse.setRsulte(courseService.save(courseDTO));
          apiResponse.setCode(ErrorCode.SUCCESS.getCode());
          apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
          return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/Get/By/{id}")
    public  ResponseEntity<ApiResponse<Course>> GetById(@PathVariable Integer id){
           ApiResponse<Course> apiResponse = new ApiResponse<>();
           apiResponse.setRsulte(courseService.GetByID(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/Update/By/{id}")
    public  ResponseEntity<ApiResponse<Course>> UpdateByID(@PathVariable Integer id ,@RequestBody CourseDTO courseDTO){
        ApiResponse<Course> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.update(id, courseDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/Get/All/Course")
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourse(){
        ApiResponse<List<Course>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.getCourse());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @DeleteMapping("/Delete/By/{id}")
    public  ResponseEntity<ApiResponse<Boolean>> DeleteById(@PathVariable Integer id){
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(courseService.DeleteById(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
