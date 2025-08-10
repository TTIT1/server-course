package com.example.springjpa.service;

import com.example.springjpa.dto.ResourceDTO;
import com.example.springjpa.model.Lecture;
import com.example.springjpa.model.Resource;
import com.example.springjpa.repository.LectureRepository;
import com.example.springjpa.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    @Autowired
    ResourceRepository resourceService;
    @Autowired
    LectureRepository lectureRepository;
public ResourceDTO add(ResourceDTO resourceDTO){
    Lecture lecture = lectureRepository.findById(resourceDTO.getLectureid())
            .orElseThrow(()->new RuntimeException("null"));
    if(lecture!=null){
        Resource resource = new Resource();
        resource.setName(resourceDTO.getName());
        resource.setUrl(resourceDTO.getUrl());
        resource.setSize(resourceDTO.getSize());

        resource.setLecture(lecture);

        resourceService.save(resource);
    }
    return  resourceDTO;
}

}
