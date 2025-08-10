package com.example.springjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
//@DiscriminatorColumn(name = "resource_type")// đặt tên cho cột chứ các dữ  liệu lớp con kế thừa vì các lớp con với lớp cha
// chung trong 1 bảng vì Sigle table mà bên các lops con thì có anotation khác để phân biệt data DiscriminatorValue
public class Resource {
    @Id
    @GeneratedValue
    private  Integer id;
    @Column(name = "n_name",unique = true,nullable = false)
    private  String name;
    @Column(name = "s_size")
    private  int size;
    @Column(name = "u_url")
    private String url;
    @OneToOne(cascade = CascadeType.ALL)// KHI QUAN HỆ 1 VS 1 THÊM NÀY ĐỂ LẤY ID LECTURE
    @JoinColumn(name = "lecture_id")
    private  Lecture lecture  ;

}
