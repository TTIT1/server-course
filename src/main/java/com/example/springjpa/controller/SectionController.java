package com.example.springjpa.controller;

import com.example.springjpa.dto.SectionDTO;
import com.example.springjpa.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@RestController
@RequestMapping("api/v2")
public class SectionController {
    @Autowired
    SectionService sectionService;

    @GetMapping
    public List<SectionDTO> getAllSection(){
        return sectionService.getAllSection();
    }

    @PostMapping("/add/section")
    public ResponseEntity<?> save(@RequestBody SectionDTO sectionDTO){
        try {
            SectionDTO sectionDTO1 = sectionService.add(sectionDTO);
            return ResponseEntity.status(HttpStatus.OK).body("oK : "+sectionDTO1);
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }


    }
    @GetMapping("/{id}")
    public SectionDTO getOne(@PathVariable Integer id) {
        try {
            SectionDTO sectionDTO = sectionService.getSectionById(id);
            return sectionDTO;
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SectionDTO sectionDTO) {
        try {
            SectionDTO sectionDTO1 = sectionService.updateSectionById(id, sectionDTO);
            return ResponseEntity.status(HttpStatus.OK).body("oK : "+sectionDTO1);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
}
