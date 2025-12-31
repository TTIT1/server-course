package com.example.springjpa.controller;



import com.example.springjpa.dto.resquest.SectionDTO;
import com.example.springjpa.service.SectionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.springjpa.dto.response.ApiResponse;
import com.example.springjpa.exception.ErrorCode;

import java.util.List;

@RestController
@RequestMapping("/api/section")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SectionController {

    SectionService sectionService;

    // CREATE section – coi như thao tác trên course => course.create / update_any
    @PreAuthorize("hasAnyAuthority('course.create','course.update_any') or hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<SectionDTO>> add(@RequestBody SectionDTO sectionDTO) {
        ApiResponse<SectionDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(sectionService.addSection(sectionDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    // GET ALL – xem cấu trúc course
    @PreAuthorize("hasAnyAuthority('course.view_all','course.view_free','course.view_purchased') or hasRole('ADMIN')")
    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<SectionDTO>>> getAll() {
        ApiResponse<List<SectionDTO>> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(sectionService.getAllSection());
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }

    // GET BY ID
    @PreAuthorize("hasAnyAuthority('course.view_all','course.view_free','course.view_purchased') or hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<SectionDTO>> getById(@PathVariable String id) {
        ApiResponse<SectionDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(sectionService.getSectionById(id));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }

    // UPDATE – course.update_own / update_any
    @PreAuthorize("hasAnyAuthority('course.update_own','course.update_any') or hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<SectionDTO>> update(@PathVariable String id,
                                                          @RequestBody SectionDTO sectionDTO) {
        ApiResponse<SectionDTO> apiResponse = new ApiResponse<>();
        apiResponse.setRsulte(sectionService.updateSection(id, sectionDTO));
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages(ErrorCode.SUCCESS.getMessage());
        return ResponseEntity.ok(apiResponse);
    }

    // DELETE – course.delete_own / delete_any
    @PreAuthorize("hasAnyAuthority('course.delete_own','course.delete_any') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        sectionService.deleteSection(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.SUCCESS.getCode());
        apiResponse.setMessages("Deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
