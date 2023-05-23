package com.example.restdemo.mapper;

import com.example.restdemo.dto.FeedbackByOrganisationDTO;
import com.example.restdemo.dto.FeedbackDTO;
import com.example.restdemo.entity.Feedback;

import java.util.HashSet;
import java.util.Set;

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

    public static FeedbackDTO mapToFeedbackDTO(Feedback feedback) {
        return FeedbackDTO.builder()
                .feedbackId(feedback.getId())
                .instructorId(feedback.getInstructor().getId())
                .courseId(feedback.getCourse().getId())
                .rating(feedback.getRating())
                .review(feedback.getReview())
                .build();
    }
}
