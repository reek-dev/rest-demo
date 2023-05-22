package com.example.restdemo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleByIdDTO {

    private Long id;
    private String date;
    private String time;
    private Integer duration;
    private String courseName;
    private String instructorName;
}
