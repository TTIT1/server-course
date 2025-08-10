package com.example.springjpa.service;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.TextDTO;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.model.Text;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.ResourceRepository;
import com.example.springjpa.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextService {
    @Autowired
    TextRepository textRepository;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    LectureRepository lectureRepository;
   public TextDTO toModel(Text text){
           TextDTO textDTO = new TextDTO();
           textDTO.setId(text.getId());
           textDTO.setName(text.getName());
           textDTO.setUrl(text.getUrl());
           textDTO.setText(text.getConText());
           textDTO.setSize(text.getSize());
           textDTO.setLectureid(text.getLecture().getId());

       return textDTO;
   }
    public TextDTO add(TextDTO textDTO) {
            Lecture lecture = lectureRepository.findById(textDTO.getLectureid()).orElseThrow(() ->
                    new RuntimeException("I don't find data lecture id = " + textDTO.getLectureid()));
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
            Text text = textRepository.findById(textDTO.getId())
                    .orElseThrow(()->new notification("Server don't data whith id = "+textDTO.getId()));
            try {
                text.setConText(textDTO.getText());
                textRepository.save(text);

            }catch (Exception e){
                throw new RuntimeException(e.getMessage());

            }
            return textDTO;
      }
      public List<TextDTO> getAll() {
        return textRepository.findAll().stream().map(textDTO -> toModel(textDTO))
                .collect(Collectors.toList());
      }

}