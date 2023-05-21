package com.example.restdemo.mapper;

import com.example.restdemo.dto.FeedbackByOrganisationDTO;
import com.example.restdemo.entity.Feedback;

public class FeedbackMapper {

    public static FeedbackByOrganisationDTO mapToFeedbackByOrganisationDTO(Feedback feedback) {

        return FeedbackByOrganisationDTO.builder()
                .feedbackId(feedback.getId())
                .instructorName(feedback.getInstructor().getFirstName() + " " + feedback.getInstructor().getLastName())
                .review(feedback.getReview())
                .courseName(feedback.getCourse().getCourseName())
                .rating(feedback.getRating())
                .build();
    }
}
