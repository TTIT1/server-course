package com.example.springjpa.model.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Resource {

    @Id
    @Column(updatable = false, nullable = false)
    private String id;


    @Column(name = "n_name", unique = true, nullable = false)
    String name;

    @Column(name = "s_size")
    int size;

    @Column(name = "u_url")
    String url;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id")
    @JsonIgnore
    Lecture lecture;
  
    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = new ULID().nextULID();
        }
    }
}
