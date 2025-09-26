package com.example.springjpa.service.Impl;


import com.example.springjpa.dto.resquest.SectionDTO;
import com.example.springjpa.exception.AppExcepotion;
import com.example.springjpa.exception.ErrorCode;
import com.example.springjpa.model.Course;
import com.example.springjpa.model.Section;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.repository.SectionRepository;
import com.example.springjpa.service.SectionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class SectionServiceImpl implements SectionService {

      SectionRepository sectionRepository;
      CourseRepository courseRepository;

    private SectionDTO toModel(Section section) {
        SectionDTO dto = new SectionDTO();
        dto.setId(section.getId());
        dto.setName(section.getName());
        dto.setOrder(section.getOrder());
        dto.setCourseId(section.getCourse().getId());
        return dto;
    }

    @Override
    public SectionDTO addSection(SectionDTO sectionDTO) {
        Course course = courseRepository.findById(sectionDTO.getCourseId())
                .orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));

        Section section = new Section();
        section.setName(sectionDTO.getName());
        section.setOrder(sectionDTO.getOrder());
        section.setCourse(course);

        sectionRepository.save(section);
        return toModel(section);
    }

    @Override
    public List<SectionDTO> getAllSection() {
        return sectionRepository.findAll()
                .stream()
                .map(this::toModel)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public SectionDTO getSectionById(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));
        return toModel(section);
    }

    @Override
    public SectionDTO updateSection(Long id, SectionDTO sectionDTO) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));

        section.setName(sectionDTO.getName());
        section.setOrder(sectionDTO.getOrder());

        // nếu muốn đổi course
        if (sectionDTO.getCourseId() != null) {
            Course course = courseRepository.findById(sectionDTO.getCourseId())
                    .orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));
            section.setCourse(course);
        }

        sectionRepository.save(section);
        return toModel(section);
    }

    @Override
    public void deleteSection(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new AppExcepotion(ErrorCode.NOT_FOUND));
        sectionRepository.delete(section);
    }
}
