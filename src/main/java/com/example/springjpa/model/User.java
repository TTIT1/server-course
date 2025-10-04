package com.example.springjpa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.ObjectUtils;


@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Table(name = "User_Se")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity{
    @Column(name = "g_email", unique = true, nullable = false, length = 255)
    String gmail;
    @Column(name = "Password",unique = true,nullable = false)
         String PassWordUser;
}
