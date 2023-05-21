package com.example.restdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFeedbackDTO {

    private Long organisationId;
    private Long instructorId;
    private Long courseId;
    private Integer rating;
    private String review;
}
