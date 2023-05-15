package com.example.restdemo.service;

import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.User;

import java.util.List;

public interface CourseService {
    public Course createCourse(Course newCourse);

//    public List<CourseIdAndNameDTO> getAllCourseIdAndName();
//
//    public List<CoursesListDTO> getAllCoursesList();
//
//    public CourseDTO getCourseDTOById(Long id);

    public Course getCourseById(Long id);


    Course assignFaculty(Course possibleExistingCourse, User possibleExistingInstructor);

}
