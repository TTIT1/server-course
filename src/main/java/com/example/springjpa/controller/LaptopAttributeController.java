package com.example.springjpa.controller;

import com.example.springjpa.dto.LaptopAttributeDTO;
import com.example.springjpa.service.LaptopAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laptop/attribute")
public class LaptopAttributeController {
    @Autowired
    LaptopAttributeService laptopAttributeService;
    @PostMapping("/add")
    public ResponseEntity<?> creatSave (@RequestBody LaptopAttributeDTO laptopAttributeDTO){
        try {
            LaptopAttributeDTO laptopAttributeDTO1 = laptopAttributeService.addNewLaptop(laptopAttributeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(laptopAttributeDTO1);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
  @GetMapping("/get/add")
    public ResponseEntity<?> getAll(){
        try {
            List<LaptopAttributeDTO> laptopAttributeDTOS =  laptopAttributeService.laptopAttributeDTOS();
            return ResponseEntity.status(HttpStatus.OK).body(laptopAttributeDTOS);

        } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
   }
   @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody LaptopAttributeDTO laptopAttributeDTO,@PathVariable Integer id){
        try {
            LaptopAttributeDTO  laptopAttributeDTO1 = laptopAttributeService.update(id,laptopAttributeDTO);
            return ResponseEntity.status(HttpStatus.OK).body(laptopAttributeDTO1);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
   }
}
