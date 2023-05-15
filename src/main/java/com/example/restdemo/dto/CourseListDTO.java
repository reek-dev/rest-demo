package com.example.restdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseListDTO {

    private Long CourseId;
    private String CourseName;
    private String CourseDuration;
    private String CourseLevel;
    private String CourseCategoryName;
    private Set<String> InstructorName;
    private String CourseFormat;
    private Integer CourseFee;
}
