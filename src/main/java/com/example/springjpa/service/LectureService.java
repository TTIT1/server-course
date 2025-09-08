package com.example.springjpa.service;

import com.example.springjpa.dto.LectureDTO;
import com.example.springjpa.dto.SectionDTO;

import java.util.List;

public interface LectureService {
    LectureDTO addLecture (LectureDTO lectureDTO);
    List<LectureDTO> getAllLecture();
    LectureDTO update (Integer id , LectureDTO lectureDTO);
    LectureDTO getbyId(Integer id);
    Boolean delete(Integer id);
}
