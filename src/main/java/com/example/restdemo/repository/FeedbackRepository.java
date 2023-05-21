package com.example.restdemo.repository;

import com.example.restdemo.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("select f from Feedback f where f.organisation.id = :organisationId")
    List<Feedback> findFeedbacksByOrganisation(@Param("organisationId") Long organisationId);

    @Query("select f from Feedback f where f.instructor.id = :instructorId")
    List<Feedback> findFeedbackByInstructor(@Param("instructorId") Long instructorId);
}
