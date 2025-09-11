package com.example.springjpa.controller;

import com.example.springjpa.dto.ApiResponse;
import com.example.springjpa.dto.AuthorDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Author;
import com.example.springjpa.service.AuthorService;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import io.swagger.v3.core.model.ApiDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<AuthorDTO>>> getAllApiResponseResponseEntity(){
                    ApiResponse<List<AuthorDTO>> apiResponse = new ApiResponse<>();
                    apiResponse.setRsulte(authorService.getAll());
                    apiResponse.setCode(ErrorCode.SUCCESS.getCode());
                    apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
                    return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
    @PostMapping("/add/new/author")
    public ResponseEntity<ApiResponse<AuthorDTO>>AddApiResponseResponseEntity(@RequestBody AuthorDTO authorDTO){
                    ApiResponse<AuthorDTO> apiResponse = new ApiResponse<>();

                     apiResponse.setRsulte(authorService.saveAuthor(authorDTO));
                        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
                        apiResponse.setCode(ErrorCode.NOT_FOUND.getCode());
                    return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Author>> UpdateResponseResponseEntity(@RequestBody AuthorDTO authorDTO,@PathVariable Integer id){
            ApiResponse<Author> apiResponse = new ApiResponse<>();
            apiResponse.setRsulte(authorService.update(authorDTO,id));
            apiResponse.setCode(ErrorCode.SUCCESS.getCode());
            apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
            return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/get/by/id")
     public ResponseEntity<ApiResponse<Author>> getbyid(@PathVariable Integer id){
             ApiResponse<Author> apiResponse = new ApiResponse<>();
             apiResponse.setRsulte(authorService.getAuthorById(id));
             apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
             apiResponse.setCode(ErrorCode.SUCCESS.getCode());
             return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
