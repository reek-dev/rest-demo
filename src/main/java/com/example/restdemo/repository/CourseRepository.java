package com.example.restdemo.repository;

import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c")
    List<Course> getAllCourses();

    Boolean existsByCourseNameIgnoreCase(String name);

    @Query("select c from Course c where c.organisation.id = :organizationId")
    List<Course> findCoursesByOrganizationId(@Param("organizationId") Long organizationId);

    @Query("select c from Course c join c.associatedUsers u where u.id = :instructorId")
    List<Course> findCourseByInstructor(@Param("instructorId") Long instructorId);

    @Query("select c from Course c join c.feedbacks f where f.id = :feedbackId")
    Course fetchCourseDetailsByFeedback(@Param("feedbackId") Long feedbackId);

}
