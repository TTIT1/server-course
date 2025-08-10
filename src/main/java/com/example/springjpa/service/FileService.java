package com.example.springjpa.service;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.FileDTO;
import com.example.springjpa.dto.ResourceDTO;
import com.example.springjpa.model.File;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.model.Resource;
import com.example.springjpa.repository.FileRepository;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    ResourceRepository resourceRepository;
    public FileDTO save(FileDTO fileDTO){
        Lecture lecture = lectureRepository.findById(fileDTO.getLectureid()).orElseThrow(()->new notification("Server don't find data "));

        try {
            File file = new File();
            file.setName(fileDTO.getName());
            file.setSize(fileDTO.getSize());
            file.setUrl(fileDTO.getUrl());
            file.setType(fileDTO.getType());
            file.setLecture(lecture);
            fileRepository.save(file);
            return  toModelFileDTO(file);

        }catch (Exception e){
            throw new notification("Server đang bảo chì tính năng này");
        }


    }
    public FileDTO updateFileById( FileDTO fileDTO) {
            Optional<File> file = fileRepository.findById(fileDTO.getId());
            if(file.isPresent()){
                File f = file.get();
                f.setType(fileDTO.getType());
                fileRepository.save(f);
                return fileDTO;
            }
            return null;
    }

    public  FileDTO toModelFileDTO(File file){
        FileDTO fileDTO = new FileDTO();
        fileDTO.setUrl(file.getUrl());
        fileDTO.setName(file.getName());
        fileDTO.setId(file.getId());
        fileDTO.setType(file.getType());
        fileDTO.setSize(file.getSize());
        fileDTO.setLectureid(file.getLecture().getId());
        return fileDTO;
    }
    public List<FileDTO> findAllFiles() {
        return fileRepository.findAll().stream().map(this::toModelFileDTO).collect(Collectors.toList());

    }


}
