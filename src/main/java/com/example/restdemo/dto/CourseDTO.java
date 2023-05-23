package com.example.restdemo.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDTO {

    private Long courseId;
    private String courseName;
    private Long courseCategoryId;
    private String courseDescription;
    private Integer courseDuration;
    private String courseLevel;
    private Integer courseFees;
    private Integer enrollment;
    private String prerequisites;
    private Set<Long> instructorIds;
    private String courseFormat;
    private String startDate;
    private String endDate;
}
