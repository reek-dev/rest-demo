package com.example.restdemo.service;

import com.example.restdemo.entity.CourseCategory;

public interface CourseCategoryService {

    public CourseCategory createCourseCategory(CourseCategory newCourseCategory);

    public CourseCategory getCourseCategoryById(Long id);
}
