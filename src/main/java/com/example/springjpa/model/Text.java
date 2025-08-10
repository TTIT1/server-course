package com.example.springjpa.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
//@DiscriminatorValue("T")
@PrimaryKeyJoinColumn(name = "Text_id")
public class Text extends  Resource{
    //@Column(name = "n_name", unique = true, nullable = false)

    private  String conText;
}
