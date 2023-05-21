package com.example.restdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetAssignmentDTO {
    private Long assignmentId;
    private String title;
    private Long courseId;
    private Long instructorId;
    private Integer maxPoint;
    private String fileTypeAllowed;
    private Integer maxFileSize;
    private String dueDate;
    private String fileName;
    private String assignmentType;
}
