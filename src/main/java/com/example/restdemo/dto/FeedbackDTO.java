package com.example.restdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FeedbackDTO {
    private Long feedbackId;
    private Long instructorId;
    private Long courseId;
    private Integer rating;
    private String review;
}
