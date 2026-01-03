package com.example.springjpa.model.order.dto.resquest;

import java.math.BigDecimal;

import com.example.springjpa.enums.order.OrderStatus;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseOrderRequest {
    String userId;
    String courseId;
    BigDecimal price;
    OrderStatus status;
}
