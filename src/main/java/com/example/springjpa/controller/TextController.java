package com.example.springjpa.controller;

import com.example.springjpa.dto.response.ApiResponse;

import com.example.springjpa.dto.resquest.TextDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.service.TextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/text")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TextController {

     TextService textService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<TextDTO>> addText(@RequestBody TextDTO textDTO) {
        ApiResponse<TextDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(textService.add(textDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @PutMapping("/update")
    public ResponseEntity<ApiResponse<TextDTO>> updateText(@RequestBody TextDTO textDTO) {
        ApiResponse<TextDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(textService.update(textDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<TextDTO>>> getAllTexts() {
        ApiResponse<List<TextDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(textService.getAll());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }
}
