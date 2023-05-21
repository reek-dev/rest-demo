package com.example.restdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AssignmentByOrganisationDTO {
    private Long assignmentId;
    private String title;
    private String instructorName;
    private String courseName;
    private String dueDate;
    private String assignmentType;
}
