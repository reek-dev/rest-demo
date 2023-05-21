package com.example.restdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAssignmentDTO {
    private Long organisationId;
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
