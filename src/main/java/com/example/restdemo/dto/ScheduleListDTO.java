package com.example.restdemo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleListDTO {

    private Long sessionId;
    private String courseName;
    private String date;
    private String time;
    private String duration;
    private String instructorName;
}
