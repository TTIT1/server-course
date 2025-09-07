package com.example.springjpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springjpa.dto.ApiResponse;
import com.example.springjpa.dto.SectionDTO;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Section;
import com.example.springjpa.service.SectionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/section")
@RequiredArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    // CREATE
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<SectionDTO>> addSection(@RequestBody SectionDTO sectionDTO) {
        ApiResponse<SectionDTO> response = new ApiResponse<>();
        response.setRsulte(sectionService.add(sectionDTO));
        response.setCode(ErrorCode.SUCCESS.getCode());
        response.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(response);
    }

    // READ - Get all
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<SectionDTO>>> getAllSections() {
        ApiResponse<List<SectionDTO>> response = new ApiResponse<>();
        response.setRsulte(sectionService.getAllSection());
        response.setCode(ErrorCode.SUCCESS.getCode());
        response.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(response);
    }

    // READ - Get by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SectionDTO>> getSectionById(@PathVariable Integer id) {
        ApiResponse<SectionDTO> response = new ApiResponse<>();
        response.setRsulte(sectionService.getSectionById(id));
        response.setCode(ErrorCode.SUCCESS.getCode());
        response.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<SectionDTO>> updateSection(@PathVariable Integer id,
                                                                 @RequestBody SectionDTO sectionDTO) {
        ApiResponse<SectionDTO> response = new ApiResponse<>();
        response.setRsulte(sectionService.updateSectionById(id, sectionDTO));
        response.setCode(ErrorCode.SUCCESS.getCode());
        response.setMessages(ErrorCode.SUCCESS.getMessages());
        return ResponseEntity.ok(response);
    }
//
//    // DELETE
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<ApiResponse<Void>> deleteSection(@PathVariable Integer id) {
//        ApiResponse<Void> response = new ApiResponse<>();
//        sectionService.deleteSection(id);
//        response.setCode(ErrorCode.SUCCESS.getCode());
//        response.setMessages("Section deleted successfully");
//        return ResponseEntity.ok(response);
//    }
}
