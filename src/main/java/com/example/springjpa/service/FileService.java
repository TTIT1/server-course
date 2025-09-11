package com.example.springjpa.service;

import com.example.springjpa.dto.FileDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.File;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.repository.FileRepository;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface FileService {
    List<FileDTO> findAllFiles();
    FileDTO updateFileById( FileDTO fileDTO);
    FileDTO save(FileDTO fileDTO);

}
