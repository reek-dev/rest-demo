package com.example.restdemo.mapper;

import com.example.restdemo.dto.CourseDTO;
import com.example.restdemo.dto.CourseIdAndNameDTO;
import com.example.restdemo.dto.CourseListDTO;
import com.example.restdemo.dto.CreateCourseDTO;
import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.User;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class CourseMapper {

    public static CourseDTO mapToCourseDTO(Course course) {

        Set<Long> instructorIds = new HashSet<>();

        for (User user : course.getAssociatedUsers()) {
            if (user.getRole().toString().equals("TEACHER"))
                instructorIds.add(user.getId());
        }

        return new CourseDTO(
                course.getId(),
                course.getCourseName(),
                course.getCategory().getId(),
                course.getCourseDescription(),
                course.getCourseDuration(),
                course.getCourseLevel().toString(),
                course.getCourseFees(),
                course.getEnrollment(),
                course.getPrerequisites(),
                instructorIds,
                course.getCourseFormat().toString(),
                course.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                course.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        );
    }

    public static CourseListDTO mapToCourseListDTO(Course course) {

        Set<String> instructorNames = new HashSet<>();

        for (User user : course.getAssociatedUsers()) {
            if (user.getRole().toString().equals("TEACHER"))
                instructorNames.add(user.getFirstName() + " " + user.getLastName());
        }

        return new CourseListDTO(
                course.getId(),
                course.getCourseName(),
                String.valueOf(course.getCourseDuration()),
                course.getCourseLevel().toString(),
                course.getCategory().getCategoryName(),
                instructorNames,
                course.getCourseFormat().toString(),
                course.getCourseFees()
        );

    }

    public static CreateCourseDTO mapToCreateCourseDTO(Course course) {

        Set<Long> ids = new HashSet<>();
        for (User user : course.getAssociatedUsers()) {
            ids.add(user.getId());
        }

        return CreateCourseDTO.builder()
                .organisationId(course.getOrganisation().getId())
                .courseName(course.getCourseName())
                .courseCategoryId(course.getCategory().getId())
                .courseDescription(course.getCourseDescription())
                .courseDuration(course.getCourseDuration())
                .courseLevel(course.getCourseLevel().toString())
                .courseFees(course.getCourseFees())
                .enrollment(course.getEnrollment())
                .prerequisites(course.getPrerequisites())
                .instructorIds(ids)
                .courseFormat(course.getCourseFormat().toString())
                .startDate(course.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .endDate(course.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .build();
    }

    public static CourseIdAndNameDTO mapToCourseIdAndNameDTO(Course course) {

        return CourseIdAndNameDTO.builder()
                .courseId(course.getId())
                .courseName(course.getCourseName())
                .build();
    }
}
