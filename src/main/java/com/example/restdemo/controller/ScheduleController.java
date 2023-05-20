package com.example.restdemo.controller;


import com.example.restdemo.dto.ResponseDTO;
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
    public ResponseEntity<ResponseDTO<String>> createSchedule(
            @RequestBody ScheduleDTO scheduleDTO
            ) {


        ScheduleDTO schedule = scheduleService.createSchedule(scheduleDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Schedule created successfully.");

        ResponseDTO<String> response =
                ResponseDTO.<String>builder()
                        .status("success")
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Schedule created successfully.")
                        .data(String.format("Schedule %s successfully created.", schedule))
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(response);
    }

    @GetMapping("/sessions/all/{organisationId}")
    public ResponseEntity<ResponseDTO<List<ScheduleListDTO>>> getSchedulesInOrg(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<ScheduleListDTO> scheduleListDTOS = scheduleService.fetchSessionsInOrg(organisationId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Schedules fetched successfully.");

        ResponseDTO<List<ScheduleListDTO>> response =
                ResponseDTO.<List<ScheduleListDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("Schedules fetched successfully.")
                        .data(scheduleListDTOS)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }


    @GetMapping("/getSession/{scheduleId}")
    public ResponseEntity<ResponseDTO<ScheduleByIdDTO>> getScheduleById(
            @PathVariable("scheduleId") Long scheduleId
    ) {

        ScheduleByIdDTO scheduleByIdDTO = scheduleService.fetchScheduleByIdDtoById(scheduleId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Schedule fetched successfully.");

        ResponseDTO<ScheduleByIdDTO> response =
                ResponseDTO.<ScheduleByIdDTO>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("Schedule fetched successfully.")
                        .data(scheduleByIdDTO)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }
}
