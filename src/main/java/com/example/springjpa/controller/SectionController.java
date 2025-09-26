package com.example.springjpa.controller;



import com.example.springjpa.dto.resquest.SectionDTO;
import com.example.springjpa.service.SectionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.exception.ErrorCode;

import java.util.List;

@RestController
@RequestMapping("/api/section")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SectionController {

     SectionService sectionService;

    // CREATE
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<SectionDTO>> add(@RequestBody SectionDTO sectionDTO) {
        ApiResponse<SectionDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(sectionService.addSection(sectionDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    // GET ALL
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<SectionDTO>>> getAll() {
        ApiResponse<List<SectionDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(sectionService.getAllSection());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }

    // GET BY ID
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<SectionDTO>> getById(@PathVariable Long id) {
        ApiResponse<SectionDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(sectionService.getSectionById(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<SectionDTO>> update(@PathVariable Long id,
                                                          @RequestBody SectionDTO sectionDTO) {
        ApiResponse<SectionDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(sectionService.updateSection(id, sectionDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(apiResponse);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        sectionService.deleteSection(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages("Deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
