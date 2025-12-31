package com.example.springjpa.dto.resquest;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE,makeFinal=true)
@RequiredArgsConstructor
@SuperBuilder
public class CourseDetailRequesst {

     String id;      

}
