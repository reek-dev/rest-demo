package com.example.restdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackByOrganisationDTO {

    private Long feedbackId;
    private String instructorName;
    private String review;
    private String courseName;
    private Integer rating;
}
