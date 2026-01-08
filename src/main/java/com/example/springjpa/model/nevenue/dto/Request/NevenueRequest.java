package com.example.springjpa.model.nevenue.dto.Request;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level=AccessLevel.PRIVATE)
public class NevenueRequest {
    List<String> authorId;
    String courseId;
    String couresorderId;
    
    
}
