package com.example.springjpa.service;

import com.example.springjpa.dto.TextDTO;

import java.util.List;


public interface TextService {
    TextDTO add(TextDTO textDTO);
    TextDTO update(TextDTO textDTO);
    List<TextDTO> getAll();

}