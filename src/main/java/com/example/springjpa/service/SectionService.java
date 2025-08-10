package com.example.springjpa.service;

import com.example.springjpa.config.notification;
import com.example.springjpa.dto.SectionDTO;
import com.example.springjpa.model.Course;
import com.example.springjpa.model.Section;
import com.example.springjpa.repository.CourseRepository;
import com.example.springjpa.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectionService {
    private SectionDTO toModel(Section section){
        SectionDTO sectionDTO = new SectionDTO();
        sectionDTO.setId(section.getId());
        sectionDTO.setName(section.getName());
        sectionDTO.setOrder(section.getOrder());
        sectionDTO.setCourseId(section.getCourse().getId());

      return  sectionDTO;
    }
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    CourseRepository courseRepository;
    public SectionDTO add (SectionDTO  sectionDTO) {

         Course course = courseRepository.findById(sectionDTO.getCourseId()).orElseThrow(()->
                 new notification("No dataa"));

         try {
             Section section = new Section();
             section.setName(sectionDTO.getName());
             section.setOrder(sectionDTO.getOrder());
             section.setCourse(course);
             sectionRepository.save(section);
             return toModel(section);
         }catch (Exception e){
             throw new notification("Something went wrong");
         }


    }
    public List<SectionDTO> getAllSection(){
        return  sectionRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toUnmodifiableList());
    }
    public SectionDTO getSectionById(int id){
        Section section = sectionRepository.getOne(id);
        return toModel(section);
    }
    public SectionDTO updateSectionById(int id, SectionDTO sectionDTO) {
        Section section = sectionRepository.getOne(id);
        section.setName(sectionDTO.getName());
        section.setOrder(sectionDTO.getOrder());
        sectionRepository.save(section);
        return toModel(section);

    }

}
