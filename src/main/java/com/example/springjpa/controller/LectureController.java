package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.LectureDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.LectureService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecture")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LectureController {
      LectureService lectureService;

     // CREAT
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<LectureDTO>> creatLecture(@RequestBody LectureDTO lectureDTO){
             ApiResponse<LectureDTO> apiResponse = new ApiResponse<>();
                apiResponse.setRsulte(lectureService.addLecture(lectureDTO));
                apiResponse.setCode(ErrorCode.SUCCESS.getCode());
                apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
                return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    //Get
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<LectureDTO>>> getAllLecture(){
        ApiResponse<List<LectureDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(lectureService.getAllLecture());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    //getbyid
    @GetMapping("/get/by/{id}")
    public ResponseEntity<ApiResponse<LectureDTO>> getAllLecture(@PathVariable Integer id){
        ApiResponse<LectureDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(lectureService.getbyId(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @PutMapping("/update/by/{id}")
    public ResponseEntity<ApiResponse<LectureDTO>> update (@PathVariable Integer id, @RequestBody LectureDTO LectureDTO){
        ApiResponse<LectureDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(lectureService.update(id,LectureDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    //Dedele
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Boolean>> aBoolean (@PathVariable Integer id){
           ApiResponse<Boolean> apiResponse = new ApiResponse<>();
          apiResponse.setRsulte(lectureService.delete(id));
          apiResponse.setCode(ErrorCode.SUCCESS.getCode());
          apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
         return   ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


}
