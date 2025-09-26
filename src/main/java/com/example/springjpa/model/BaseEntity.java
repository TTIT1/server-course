package com.example.springjpa.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PACKAGE)
public class BaseEntity {
    @Id
    @GeneratedValue
     Integer id;
    @CreatedDate
     LocalDateTime creatAt;
    @UpdateTimestamp
     LocalDateTime lassModifiedAt;

     String createBy;
     String lastMotifiedBy;
}
