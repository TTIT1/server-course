package com.example.springjpa.service;

import com.example.springjpa.dto.LectureDTO;

import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Lecture;
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
                      .orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
              try {

                          Lecture lecture = new Lecture();
                          lecture.setName(lectureDTO.getName());
                          lecture.setSection(section);
                         lectureRepository.save(lecture);

              }catch (Exception e){

                     throw new AppExcepotion(ErrorCode.INVALID_INPUT);
              }
              return  lectureDTO;

       }
       public List<LectureDTO> getAll(){
              return  lectureRepository.findAll().stream().map(this::toModelLecture).collect(Collectors.toList());
       }

       // Service
       public LectureDTO updateLecture(LectureDTO lectureDTO) {
              Lecture lecture = lectureRepository.findById(lectureDTO.getId())
                      .orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));
              try {
                     lecture.setName(lectureDTO.getName());
                     return toModelLecture(lectureRepository.save(lecture));
              } catch (Exception e) {
                  throw    new AppExcepotion(ErrorCode.INVALID_INPUT);
              }
       }



}
