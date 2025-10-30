package com.example.springjpa.service;


import com.example.springjpa.dto.resquest.SectionDTO;
import com.example.springjpa.model.Section;

import java.util.List;
public interface SectionService {
    SectionDTO addSection(SectionDTO sectionDTO);
    List<SectionDTO> getAllSection();
    SectionDTO getSectionById(String id);
    SectionDTO updateSection(String id, SectionDTO sectionDTO);
    void deleteSection(String id);
}
