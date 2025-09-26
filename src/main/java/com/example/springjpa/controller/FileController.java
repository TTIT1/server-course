package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;

import com.example.springjpa.dto.resquest.FileDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.FileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileController {

        FileService fileService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<FileDTO>>> getAllFiles() {
        ApiResponse<List<FileDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(fileService.findAllFiles());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse<FileDTO>> addFile(@RequestBody FileDTO fileDTO) {
        ApiResponse<FileDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(fileService.save(fileDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @PutMapping("/update")
    public ResponseEntity<ApiResponse<FileDTO>> updateFile(@RequestBody FileDTO fileDTO) {
        ApiResponse<FileDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(fileService.updateFileById(fileDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }
}
