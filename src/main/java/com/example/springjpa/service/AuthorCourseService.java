package com.example.springjpa.service;

import com.example.springjpa.dto.response.AuthorCourseResponse;
import com.example.springjpa.dto.resquest.AuthorCourseRequest;

public interface AuthorCourseService {
    AuthorCourseResponse add(AuthorCourseRequest request);
}
