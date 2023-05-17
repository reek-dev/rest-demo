package com.example.restdemo.controller;


import com.example.restdemo.dto.ScheduleByIdDTO;
import com.example.restdemo.dto.ScheduleDTO;
import com.example.restdemo.dto.ScheduleListDTO;
import com.example.restdemo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/create")
    public ResponseEntity<String> createSchedule(
            @RequestBody ScheduleDTO scheduleDTO
            ) {
        ScheduleDTO schedule = scheduleService.createSchedule(scheduleDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Schedule created successfully.");
        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body("Schedule created with instructor: `" + schedule.getInstructorId() + "`");
    }

    @GetMapping("/sessions/all/{organisationId}")
    public ResponseEntity<List<ScheduleListDTO>> getSchedulesInOrg(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<ScheduleListDTO> scheduleListDTOS = scheduleService.fetchSessionsInOrg(organisationId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Schedules fetched successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(scheduleListDTOS);
    }


    @GetMapping("/getSession/{scheduleId}")
    public ResponseEntity<ScheduleByIdDTO> getScheduleById(
            @PathVariable("scheduleId") Long scheduleId
    ) {

        ScheduleByIdDTO scheduleByIdDTO = scheduleService.fetchScheduleByIdDtoById(scheduleId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Schedule fetched successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(scheduleByIdDTO);
    }
}
