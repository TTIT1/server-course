package com.example.springjpa.model.course;

import de.huxhorn.sulky.ulid.ULID;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
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
    @Column(updatable = false, nullable = false)
    private String id;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = new ULID().nextULID();
        }
    }
}