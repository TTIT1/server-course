package com.example.springjpa.service;


import java.util.List;

import com.example.springjpa.dto.resquest.TextDTO;


public interface TextService {
    TextDTO add(TextDTO textDTO,String file);
    TextDTO update(TextDTO textDTO);
    List<TextDTO> getAll();

}