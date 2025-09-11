package com.example.springjpa.service;

import com.example.springjpa.dto.TextDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.model.Text;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.ResourceRepository;
import com.example.springjpa.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public interface TextService {
    TextDTO add(TextDTO textDTO);
    TextDTO update(TextDTO textDTO);
    List<TextDTO> getAll();

}