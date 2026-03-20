package com.example.springjpa.model.nevenue.service.Impl;

import org.springframework.stereotype.Service;

import com.example.springjpa.model.nevenue.dto.Request.NevenueRequest;
import com.example.springjpa.model.nevenue.mapper.NevenueMapper;
import com.example.springjpa.model.nevenue.repository.NevenueRepository;
import com.example.springjpa.model.nevenue.service.NevenueService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class NevenueServiceImpl implements NevenueService {
     NevenueRepository nevenueRepository;
     NevenueMapper nevenueMapper;
    @Override
    public Boolean addNevenue(NevenueRequest nevenueRequest) {
          nevenueRepository.save(nevenueMapper.toNevenue(nevenueRequest));
        return true;

    }
    
}
