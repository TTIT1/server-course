package com.example.springjpa.dto.resquest;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class CouresUserRequest {
    String userId;
    String courseId;

}
