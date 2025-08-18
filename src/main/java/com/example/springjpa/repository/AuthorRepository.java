package com.example.springjpa.repository;

import com.example.springjpa.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    Optional<Author> findByEmail(String email);
    // Select * from auth where first_name = 'nameFind'
    List<Author> findAllByfirstName(String nameFind);
    List<Author> findAllByfirstNameIgnoreCase(String nameFind);


}
/*
find…By<Property><Keyword>
read…By<Property><Keyword>
get…By<Property><Keyword>

Property = tên field trong Entity (ví dụ firstName, lastName, age…)
Keyword = từ khóa đặc biệt mà Spring Data hiểu (ví dụ IgnoreCase, Containing, Between, In…)


 */