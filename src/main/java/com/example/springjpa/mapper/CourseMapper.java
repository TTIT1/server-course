package com.example.springjpa.mapper;


import com.example.springjpa.dto.response.CouresAllResponse.CourseDetailResponse;
import com.example.springjpa.dto.response.CouresAllResponse.SectionResponse;
import com.example.springjpa.dto.response.CouresAllResponse.lectureResponse;
import com.example.springjpa.dto.response.CouresAllResponse.ResourceResponse;
import com.example.springjpa.dto.response.CouresAllResponse.FileResponse;
import com.example.springjpa.dto.response.CouresAllResponse.VideoResponse;
import com.example.springjpa.dto.response.CouresAllResponse.TextResponse;
import com.example.springjpa.dto.resquest.CourseDTO;
import com.example.springjpa.model.course.Course;
import com.example.springjpa.model.course.Lecture;
import com.example.springjpa.model.course.Resource;
import com.example.springjpa.model.course.File;
import com.example.springjpa.model.course.Video;
import com.example.springjpa.model.course.Text;
import com.example.springjpa.model.course.Section;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;


@Mapper(componentModel ="spring")
public interface CourseMapper {
    Course toCourse(CourseDTO courseDTO);
    Course updateCourse(@MappingTarget Course course, CourseDTO courseDTO);

   
    default CourseDetailResponse toCourseDetailDTO(Course course) {
        return new CourseDetailResponse(
                course.getAuthors().stream()
                        .map(author -> author.getFirstName())
                        .toList(),
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getPrice(),
                course.getSections().stream()
                        .map(this::toSectionDTO)
                        .toList()
        );
    }

    private SectionResponse toSectionDTO(Section section) {
        return new SectionResponse(
                section.getId(),
                section.getName(),
                section.getOrder(),
                section.getLecture().stream()
                        .map(this::toLectureResponse)
                        .toList()
        );
    }

    private lectureResponse toLectureResponse(Lecture lecture) {
     
       List<ResourceResponse> resources = lecture.getResource() == null
        ? Collections.emptyList()
        : Collections.singletonList(toResourceDTO(lecture.getResource()));

        return new lectureResponse(
                lecture.getId(),
                lecture.getName(),
                resources
        );
    }

    private ResourceResponse toResourceDTO(Resource resource) {
        if (resource == null) return null;

        if (resource instanceof File f) {
            return new ResourceResponse(
                    f.getId(),
                    "FILE",
                    new FileResponse(f.getId(), f.getName(), (long) f.getSize(), f.getUrl()),
                    null,
                    null
            );
        }
        if (resource instanceof Video v) {
            return new ResourceResponse(
                    v.getId(),
                    "VIDEO",
                    null,
                    new VideoResponse(v.getId(), v.getLength(),v.getUrl()),
                    null
            );
        }
        if (resource instanceof Text t) {
            return new ResourceResponse(
                    t.getId(),
                    "TEXT",
                    null,
                    null,
                    new TextResponse(t.getId(), t.getConText(),t.getUrl())
            );
        }


        return new ResourceResponse(
                resource.getId(),
                "UNKNOWN",
                null,
                null,
                null
        );
    }
}