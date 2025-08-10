package com.example.springjpa.controller;

import com.example.springjpa.dto.CourseDTO;
import com.example.springjpa.model.Course;
import com.example.springjpa.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class CourseController {
    @Autowired
    CourseService  courseService;
    @PostMapping("/add")
   public Course saveCourse(CourseDTO course){
       return courseService.save(course);
   }
   @GetMapping("/get/all")
    public List<CourseDTO> getaLL(){
        return courseService.getCourse();
   }
  @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id , @RequestBody CourseDTO courseDTO){
           try {

               CourseDTO course = courseService.update(id,courseDTO);
              return   ResponseEntity.status(HttpStatus.OK).body(course);

           } catch (RuntimeException e) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
           }
  }
}

