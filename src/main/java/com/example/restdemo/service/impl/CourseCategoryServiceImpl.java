package com.example.restdemo.service.impl;

import com.example.restdemo.entity.CourseCategory;
import com.example.restdemo.exception.CourseCategoryAlreadyExistsException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.repository.CourseCategoryRepository;
import com.example.restdemo.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryRepository courseCategoryRepository;

    @Override
    public CourseCategory createCourseCategory(CourseCategory newCourseCategory) {
        if (courseCategoryRepository.existsByCategoryNameIgnoreCase(newCourseCategory.getCategoryName())) {
            throw new CourseCategoryAlreadyExistsException(newCourseCategory.getCategoryName());
        }
        return courseCategoryRepository.save(newCourseCategory);
    }

    @Override
    public CourseCategory getCourseCategoryById(Long id) {
        return courseCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(id)));
    }

}
