package com.example.springjpa.service.Impl;


import com.example.springjpa.dto.resquest.TextDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.course.Lecture;
import com.example.springjpa.model.course.Text;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.ResourceRepository;
import com.example.springjpa.repository.TextRepository;
import com.example.springjpa.service.TextService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TextServiceImpl implements TextService {

    TextRepository textRepository;

    ResourceRepository resourceRepository;

   LectureRepository lectureRepository;
    public TextDTO toModel(Text text){
        TextDTO textDTO = new TextDTO();
        textDTO.setText(text.getId());
        textDTO.setName(text.getName());
        textDTO.setUrl(text.getUrl());
        textDTO.setText(text.getConText());
        textDTO.setSize(text.getSize());
        textDTO.setLectureid(text.getLecture().getId());

        return textDTO;
    }
    public TextDTO add(TextDTO textDTO) {
        Lecture lecture = lectureRepository.findById(textDTO.getLectureid()).orElseThrow(() ->
                new AppExcepotion(ErrorCode.NOT_FOUND));
        try {
            Text text = new Text();
            text.setConText(textDTO.getText());
            text.setUrl(textDTO.getUrl());
            text.setName(textDTO.getName());
            text.setSize(textDTO.getSize());
            text.setLecture(lecture);
            resourceRepository.save(text);

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return textDTO;
    }
    public TextDTO update(TextDTO textDTO) {
        Text text = textRepository.findById(textDTO.getText())
                .orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
        try {
            text.setConText(textDTO.getText());
            textRepository.save(text);

        }catch (Exception e){
            throw new AppExcepotion(ErrorCode.INVALID_INPUT);

        }
        return textDTO;
    }
    public List<TextDTO> getAll() {
        return textRepository.findAll().stream().map(textDTO -> toModel(textDTO))
                .collect(Collectors.toList());
    }
}
