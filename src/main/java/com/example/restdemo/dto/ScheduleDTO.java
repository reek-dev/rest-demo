package com.example.restdemo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {

    private Long organisationId;
    private String date;
    private String time;
    private String duration;
    private Long courseId;
    private Long instructorId;

    @Override
    public String toString() {
        return String.format("[date: %s, time: %s]", this.date, this.time);
    }
}
