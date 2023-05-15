package com.example.restdemo.repository;

import com.example.restdemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c")
    List<Course> getAllCourses();

//    @Query("select c.id, c.name from Course c")
//    List<CourseIdAndNameDTO> getAllCoursesIdAndName();

    Boolean existsByCourseNameIgnoreCase(String name);
}
