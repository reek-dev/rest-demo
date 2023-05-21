package com.example.restdemo.mapper;

import com.example.restdemo.dto.AssignmentByOrganisationDTO;
import com.example.restdemo.dto.GetAssignmentDTO;
import com.example.restdemo.entity.Assignment;

import java.time.format.DateTimeFormatter;

public class AssignmentMapper {

    public static AssignmentByOrganisationDTO mapToAssignmentByOrganisationDTO(Assignment assignment) {
        return AssignmentByOrganisationDTO.builder()
                .assignmentId(assignment.getId())
                .title(assignment.getTitle())
                .instructorName(assignment.getInstructor().getFirstName() + " " + assignment.getInstructor().getLastName())
                .courseName(assignment.getCourse().getCourseName())
                .dueDate(assignment.getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .assignmentType(assignment.getAssignmentType())
                .build();
    }

    public static GetAssignmentDTO mapToGetAssignmentDTO(Assignment assignment) {
        return GetAssignmentDTO.builder()
                .assignmentId(assignment.getId())
                .title(assignment.getTitle())
                .courseId(assignment.getCourse().getId())
                .instructorId(assignment.getInstructor().getId())
                .maxPoint(assignment.getMaxPoint())
                .fileTypeAllowed(assignment.getFileTypeAllowed())
                .maxFileSize(assignment.getMaxFileSize())
                .dueDate(assignment.getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .fileName(assignment.getFileName())
                .assignmentType(assignment.getAssignmentType())
                .build();
    }
}
