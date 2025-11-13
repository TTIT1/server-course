package com.example.springjpa.service.Impl;



import com.example.springjpa.dto.resquest.FileRequest;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;

import com.example.springjpa.model.course.File;
import com.example.springjpa.model.course.Lecture;
import com.example.springjpa.repository.FileRepository;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.ResourceRepository;
import com.example.springjpa.service.FileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class FileServiceImpl implements FileService {

    FileRepository fileRepository;

    LectureRepository lectureRepository;

    ResourceRepository resourceRepository;

    public FileRequest save(FileRequest fileDTO){
        Lecture lecture = lectureRepository.findById(fileDTO.getLectureid()).orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));

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
            throw new AppExcepotion(ErrorCode.INVALID_INPUT);
        }


    }
    public FileRequest updateFileById( FileRequest fileDTO) {
        Optional<File> file = fileRepository.findById(fileDTO.getId());
        if(file.isPresent()){
            File f = file.get();
            f.setType(fileDTO.getType());
            fileRepository.save(f);
            return fileDTO;
        }
        return null;
    }

    public  FileRequest toModelFileDTO(File file){
        FileRequest fileDTO = new FileRequest() ;
        fileDTO.setUrl(file.getUrl());
        fileDTO.setName(file.getName());
        fileDTO.setId(file.getId());
        fileDTO.setType(file.getType());
        fileDTO.setSize(file.getSize());
        fileDTO.setLectureid(file.getLecture().getId());
        return fileDTO;
    }
    public List<FileRequest> findAllFiles() {
        return fileRepository.findAll().stream().map(this::toModelFileDTO).collect(Collectors.toList());

    }

}
