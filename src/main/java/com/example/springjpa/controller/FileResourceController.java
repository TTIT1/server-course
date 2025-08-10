package com.example.springjpa.controller;

import com.example.springjpa.dto.FileDTO;
import com.example.springjpa.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/file/v1")
public class FileResourceController {
    @Autowired
    FileService fileService;

    @GetMapping("/get")
    public List<FileDTO> getFile() {
        return fileService.findAllFiles();

    }
    @PostMapping("/add/file")
    public ResponseEntity<?> addfile(@RequestBody FileDTO fileDTO){
        try {
            FileDTO fileDTO1 = fileService.save(fileDTO);
            return ResponseEntity.status(HttpStatus.OK).body("ok  :" +fileDTO);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody FileDTO fileDTO) {
        try {
            FileDTO updated = fileService.updateFileById(fileDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Update success: " + updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }
}
