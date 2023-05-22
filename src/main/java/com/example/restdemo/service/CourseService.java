package com.example.restdemo.service;

import com.example.restdemo.dto.*;
import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.User;

import java.util.List;

public interface CourseService {

    public CreateCourseDTO createCourse(CreateCourseDTO courseDTO);

    public Course getCourseById(Long id);


    Course assignFaculty(Course possibleExistingCourse, User possibleExistingInstructor);

    void saveCourse(Course possibleCourse);

    public CourseDTO getCourseDtoById(Long id);

    public List<CourseListDTO> fetchCoursesByOrgnisation(Long organisationId);

    public List<CourseCountResponseDTO> getCourseCountByCategory(Long organisationId);

    public List<CourseIdAndNameDTO> getCourseByInstructor(Long instructorId);

}
