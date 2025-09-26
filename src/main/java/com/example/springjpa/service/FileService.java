package com.example.springjpa.service;

import com.example.springjpa.dto.FileDTO;

import java.util.List;


public interface FileService {
    List<FileDTO> findAllFiles();
    FileDTO updateFileById( FileDTO fileDTO);
    FileDTO save(FileDTO fileDTO);

}
