package com.example.restdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long CourseId;
    private String CourseName;
    private Long CourseCategoryId;
    private String CourseDescription;
    private String CourseDuration;
    private String CourseLevel;
    private Integer CourseFees;
    private Integer Enrollment;
    private String Prerequisites;
    private Set<Long> InstructorId;
    private String CourseFormat;
    private LocalDate StartDate;
    private LocalDate EndDate;
}
