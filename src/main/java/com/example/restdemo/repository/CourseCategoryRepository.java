package com.example.restdemo.repository;

import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {

    Boolean existsByCategoryNameIgnoreCase(String category);

    boolean existsById(Long id);

    @Query("select cc from CourseCategory cc join cc.organisations o where o.id = :organisationId")
    List<CourseCategory> findCourseCategoriesByOrganizationId(@Param("organisationId") Long organisationId);
}
