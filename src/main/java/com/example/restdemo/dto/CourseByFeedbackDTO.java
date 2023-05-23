package com.example.restdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class CourseByFeedbackDTO {
    private String courseName;
    private Set<String> instructorName;
    private String courseDescription;
    private Integer courseFee;
}
