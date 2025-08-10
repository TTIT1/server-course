package com.example.springjpa.controller;

import com.example.springjpa.dto.TextDTO;
import com.example.springjpa.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/text/v1")
public class TextResourceController {
    @Autowired
    private TextService textService;

    @PostMapping("/add/text")
    public ResponseEntity<?> afff(@RequestBody TextDTO textDTO){
        try {
            TextDTO textDTO1 = textService.add(textDTO );
            return ResponseEntity.status(HttpStatus.OK).body("ok  :" +textDTO1);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/update/text")
    public ResponseEntity<?> update(@RequestBody TextDTO textDTO){
        try {
            TextDTO textDTO1 = textService.update(textDTO);
            return ResponseEntity.status(HttpStatus.OK).body("ok  :" +textDTO1);

        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/get/all/text")
    public List<TextDTO> getAll(){
        return textService.getAll();
    }
}
