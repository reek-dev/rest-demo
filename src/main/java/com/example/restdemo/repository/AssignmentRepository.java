package com.example.restdemo.repository;

import com.example.restdemo.entity.Assignment;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {


    @Query("select a from Assignment a where a.organisation.id = :organisationId")
    List<Assignment> fetchAssignmentByOrganisation(@Param("organisationId") Long organisationId);

    @Query("select a from Assignment a where a.instructor.id = :instructorId")
    List<Assignment> fetchAssignmentByInstructor(@Param("instructorId") Long instructorId);

    @Query("select a from Assignment a where a.organisation.id = :organisationId and a.course.id = :courseId")
    List<Assignment> fetchAssignmentByOrgAndCourse(@Param("organisationId") Long organisationId, @Param("courseId") Long courseId);

}
