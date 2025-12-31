package com.example.springjpa.model.course;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
//@DiscriminatorValue("F")
@PrimaryKeyJoinColumn(name = "File_id")
public class File extends Resource {
      String type;
}

