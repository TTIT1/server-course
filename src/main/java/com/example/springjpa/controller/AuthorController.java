package com.example.springjpa.controller;

import com.example.springjpa.dto.AuthorDTO;
import com.example.springjpa.model.Author;
import com.example.springjpa.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {
    @Autowired
    AuthorService authorService;


    @PostMapping("/add/Author")
    public ResponseEntity<?> save(@RequestBody AuthorDTO authorDTO) {
        try {
            authorService.saveAuthor(authorDTO);
            return ResponseEntity.status(HttpStatus.OK).body("ok la ");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail: "+authorDTO);
        }
    }
    @GetMapping("/get/all")
    public List<AuthorDTO> getall(){
        return authorService.getAll();
    }
    @PutMapping("/update/by/{id}")
    public ResponseEntity<?> update (@PathVariable Integer id, @RequestBody AuthorDTO authorDTO){
        try {
            Author author = authorService.update(authorDTO,id);
            return ResponseEntity.status(HttpStatus.OK).body("200 "+author);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/get/by/{id}")
    public  ResponseEntity<?> GetAuthorById(@PathVariable Integer id){
        try {
            AuthorDTO author = authorService.getAuthorById(id);

              return   ResponseEntity.status(HttpStatus.OK).body(author);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}