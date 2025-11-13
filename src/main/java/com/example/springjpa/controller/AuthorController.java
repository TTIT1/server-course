package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.dto.response.AuthorCourseResponse;
import com.example.springjpa.dto.response.AuthorResponse;
import com.example.springjpa.dto.resquest.AuthorCourseRequest;
import com.example.springjpa.dto.resquest.AuthorRequest;
import com.example.springjpa.exception.ErrorCode;


import com.example.springjpa.model.course.Author;
import com.example.springjpa.service.AuthorService;
import com.example.springjpa.service.EmailService;
import com.example.springjpa.service.OptService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.web.bind.annotation.*;


import java.util.List;
//@PreAuthorize("isAuthenticated()") // chỉ người login mới vào được
//@PreAuthorize("isAnonymous()")// chỉ người chưa login mới vào được
@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthorController {

     AuthorService authorService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add/two")
   public ApiResponse<AuthorCourseResponse> authorCourseResponseApiResponse (@RequestBody AuthorCourseRequest request){
       AuthorCourseResponse authorCourseResponse = authorService.saveAuthorCourse(request);
       return ApiResponse.<AuthorCourseResponse>builder()
               .rsulte(authorCourseResponse)
               .code(ErrorCode.SUCCESS.getCode())
               .messages(ErrorCode.SUCCESS.getMessage())
               .build();
   }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<AuthorRequest>>> getAllApiResponseResponseEntity(){
                    ApiResponse<List<AuthorRequest>> apiResponse = new ApiResponse<>();
                    apiResponse.setRsulte(authorService.getAll());
                    apiResponse.setCode(ErrorCode.SUCCESS.getCode());
                    apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
                    return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @PreAuthorize("hasAnyRole('ADMIN', 'AUTHOR')")
    @PostMapping("/add/new/author")
    public ResponseEntity<ApiResponse<AuthorRequest>>AddApiResponseResponseEntity(@RequestBody AuthorRequest authorDTO){
                    ApiResponse<AuthorRequest> apiResponse = new ApiResponse<>();

                     apiResponse.setRsulte(authorService.saveAuthor(authorDTO));
                        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
                        apiResponse.setCode(ErrorCode.NOT_FOUND.getCode());
                    return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AuthorResponse>> UpdateResponseResponseEntity(@RequestBody AuthorRequest authorDTO,@PathVariable String id){
            ApiResponse<AuthorResponse> apiResponse = new ApiResponse<>();
            apiResponse.setRsulte(authorService.update(authorDTO,id));
            apiResponse.setCode(ErrorCode.SUCCESS.getCode());
            apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
            return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    @GetMapping("/get/by/{id}")
     public ResponseEntity<ApiResponse<Author>> getbyid(@PathVariable String id){
             ApiResponse<Author> apiResponse = new ApiResponse<>();
             apiResponse.setRsulte(authorService.getAuthorById(id));
             apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
             apiResponse.setCode(ErrorCode.SUCCESS.getCode());
             return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }



}