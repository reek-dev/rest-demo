package com.example.restdemo.service;

import com.example.restdemo.dto.CourseCountResponseDTO;
import com.example.restdemo.dto.CourseDTO;
import com.example.restdemo.dto.CourseListDTO;
import com.example.restdemo.dto.CreateCourseDTO;
import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.User;

import java.util.List;

public interface CourseService {
//    public Course createCourse(Course newCourse);

    public CreateCourseDTO createCourse(CreateCourseDTO courseDTO);

//    public List<CourseIdAndNameDTO> getAllCourseIdAndName();
//
//    public List<CoursesListDTO> getAllCoursesList();
//
//    public CourseDTO getCourseDTOById(Long id);

    public Course getCourseById(Long id);


    Course assignFaculty(Course possibleExistingCourse, User possibleExistingInstructor);

    void saveCourse(Course possibleCourse);

    public CourseDTO getCourseDtoById(Long id);

    public List<CourseListDTO> fetchCoursesByOrgnisation(Long organisationId);

    public List<CourseCountResponseDTO> getCourseCountByCategory(Long organisationId);

}
