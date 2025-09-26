package com.example.springjpa.service.Impl;

import com.example.springjpa.dto.LectureDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.model.Section;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.SectionRepository;
import com.example.springjpa.service.LectureService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class LectureServiceImpl implements LectureService {
   LectureRepository lectureRepository;
 SectionRepository sectionRepository;

    public LectureDTO toModelLecture(Lecture lecture) {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setId(lecture.getId());
        lectureDTO.setName(lecture.getName());
        lectureDTO.setSectionId(lecture.getSection().getId());
        return lectureDTO;

    }

    @Override
    public LectureDTO addLecture(LectureDTO lectureDTO) {

        Section section = sectionRepository.findById(lectureDTO.getSectionId())
                .orElseThrow(() -> new AppExcepotion(ErrorCode.USER_NOT_FOUND));

        Lecture lecture = new Lecture();
        lecture.setName(lectureDTO.getName());
        lecture.setSection(section);
        lectureRepository.save(lecture);
        return toModelLecture(lecture)  ;
    }

    @Override
    public List<LectureDTO> getAllLecture() {
        List<Lecture> lectureDTOS = lectureRepository.findAll();
        return lectureDTOS.stream().map(this::toModelLecture).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public LectureDTO update(Integer id, LectureDTO lectureDTO) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));
       lecture.setName(lectureDTO.getName());
       if(lectureDTO.getSectionId()!=null){
           Section section = sectionRepository.findById(lectureDTO.getSectionId()).orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
           lecture.setSection(section);
       }
       return toModelLecture(lectureRepository.save(lecture));
    }

    @Override
    public LectureDTO getbyId(Integer id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
        return toModelLecture(lecture);
    }

    @Override
    public Boolean delete(Integer id) {
       Lecture lecture = lectureRepository.findById(id).orElseThrow(()->new AppExcepotion(ErrorCode.NOT_FOUND));
        lectureRepository.delete(lecture);
        return  true;
    }
}