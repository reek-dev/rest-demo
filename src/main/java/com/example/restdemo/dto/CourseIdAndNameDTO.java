package com.example.restdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseIdAndNameDTO {
    private Long courseId;
    private String courseName;
}
