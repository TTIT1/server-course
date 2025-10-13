package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.AuthorCourseResponse;
import com.example.springjpa.dto.resquest.AuthorCourseRequest;
import com.example.springjpa.dto.resquest.AuthorRequest;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Author;
import com.example.springjpa.model.AuthorCourse;
import com.example.springjpa.service.AuthorCourseService;
import com.example.springjpa.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthorController {
    AuthorCourseService authorCourseService;
     AuthorService authorService;
     @PostMapping("/add/two")
     public ResponseEntity<ApiResponse<AuthorCourseResponse>> addtwo(@RequestBody AuthorCourseRequest request){
                     ApiResponse<AuthorCourseResponse> apiResponse = new ApiResponse<>();
                        apiResponse.setRsulte(authorCourseService.add(request));
                        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
                        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
                        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

     }
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<AuthorRequest>>> getAllApiResponseResponseEntity(){
                    ApiResponse<List<AuthorRequest>> apiResponse = new ApiResponse<>();
                    apiResponse.setRsulte(authorService.getAll());
                    apiResponse.setCode(ErrorCode.SUCCESS.getCode());
                    apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
                    return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
    @PostMapping("/add/new/author")
    public ResponseEntity<ApiResponse<AuthorRequest>>AddApiResponseResponseEntity(@RequestBody AuthorRequest authorDTO){
                    ApiResponse<AuthorRequest> apiResponse = new ApiResponse<>();

                     apiResponse.setRsulte(authorService.saveAuthor(authorDTO));
                        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
                        apiResponse.setCode(ErrorCode.NOT_FOUND.getCode());
                    return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Author>> UpdateResponseResponseEntity(@RequestBody AuthorRequest authorDTO,@PathVariable Integer id){
            ApiResponse<Author> apiResponse = new ApiResponse<>();
            apiResponse.setRsulte(authorService.update(authorDTO,id));
            apiResponse.setCode(ErrorCode.SUCCESS.getCode());
            apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
            return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/get/by/{id}")
     public ResponseEntity<ApiResponse<Author>> getbyid(@PathVariable Integer id){
             ApiResponse<Author> apiResponse = new ApiResponse<>();
             apiResponse.setRsulte(authorService.getAuthorById(id));
             apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
             apiResponse.setCode(ErrorCode.SUCCESS.getCode());
             return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
