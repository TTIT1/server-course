package com.example.springjpa.model.auth;

import com.example.springjpa.model.course.BaseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class RefreshToken extends BaseEntity {

    @Column(name = "refreshToken" ,unique = true,nullable = false,length = 1000)
    String refreshToken;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
