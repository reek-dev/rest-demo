package com.example.restdemo.repository;

import com.example.restdemo.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {

    Boolean existsByCategoryNameIgnoreCase(String category);

    boolean existsById(Long id);
}
