package com.example.springjpa.service;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.LectureDTO;
import com.example.springjpa.dto.ResourceDTO;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.model.Resource;
import com.example.springjpa.model.Section;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecureService   {
       @Autowired
       SectionRepository sectionRepository;
       @Autowired
       LectureRepository lectureRepository;


       public LectureDTO toModelLecture(Lecture lecture){
              LectureDTO lectureDTO = new LectureDTO();
              lectureDTO.setId(lecture.getId());
              lectureDTO.setName(lecture.getName());
              lectureDTO.setSectionId(lecture.getSection().getId());


            return  lectureDTO;

       }
       public LectureDTO addLecture (LectureDTO lectureDTO){
              Section section = sectionRepository.findById(lectureDTO.getSectionId())
                      .orElseThrow(()->new RuntimeException("Server don't find data section When you want to add Lecture"));
              try {

                          Lecture lecture = new Lecture();
                          lecture.setName(lectureDTO.getName());
                          lecture.setSection(section);
                         lectureRepository.save(lecture);

              }catch (Exception e){

                     throw new RuntimeException(e.getMessage());
              }
              return  lectureDTO;

       }
       public List<LectureDTO> getAll(){
              return  lectureRepository.findAll().stream().map(this::toModelLecture).collect(Collectors.toList());
       }

       public LectureDTO updateLecture(LectureDTO lectureDTO) throws notification {
              Lecture lecture = lectureRepository.findById(lectureDTO.getId()).orElseThrow(()->new notification("I DON'T DATA"));
              try {
                     lecture.setName(lectureDTO.getName());return toModelLecture(lectureRepository.save(lecture));
              } catch (notification e) {
                     throw new RuntimeException(e);
              }
       }


}
