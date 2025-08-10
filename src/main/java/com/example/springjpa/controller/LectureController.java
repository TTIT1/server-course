package com.example.springjpa.controller;

import com.example.springjpa.dto.LectureDTO;
import com.example.springjpa.model.Course;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.service.LecureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v5")
public class LectureController {
    @Autowired
    LecureService lecureService;
    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@RequestBody LectureDTO lectureDTO) {
        try {
            LectureDTO lecture = lecureService.addLecture(lectureDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(lecture);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
   @GetMapping("/get/v1")
    public List<LectureDTO> getLecture(){
        return lecureService.getAll();
   }
    @PutMapping("/update/Lecture")
    public ResponseEntity<?> updateLecture(@RequestBody LectureDTO lectureDTO) {
        try {
          LectureDTO lectureDTO1 =   lecureService.updateLecture(lectureDTO);
          return ResponseEntity.status(HttpStatus.OK).body(lectureDTO1);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
