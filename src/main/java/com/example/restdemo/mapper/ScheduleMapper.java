package com.example.restdemo.mapper;

import com.example.restdemo.dto.ScheduleByIdDTO;
import com.example.restdemo.dto.ScheduleListDTO;
import com.example.restdemo.entity.Schedule;

import java.time.format.DateTimeFormatter;

public class ScheduleMapper {

    public static ScheduleListDTO mapToScheduleListDTO(Schedule schedule) {

        return new ScheduleListDTO(
                schedule.getId(),
                schedule.getCourse().getCourseName(),
                schedule.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                schedule.getTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                schedule.getDuration().format(DateTimeFormatter.ofPattern("HH:mm")),
                schedule.getInstructor().getFirstName() + " " + schedule.getInstructor().getLastName()
        );
    }

    public static ScheduleByIdDTO mapToScheduleByIdDTO(Schedule schedule) {

        return new ScheduleByIdDTO(
                schedule.getId(),
                schedule.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                schedule.getTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                schedule.getDuration().format(DateTimeFormatter.ofPattern("HH:mm")),
                schedule.getCourse().getCourseName(),
                schedule.getInstructor().getFirstName() + " " + schedule.getInstructor().getLastName()
        );
    }
}
