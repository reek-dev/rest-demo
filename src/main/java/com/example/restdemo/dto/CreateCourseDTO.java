package com.example.restdemo.dto;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCourseDTO {

    private Long organisationId;
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
