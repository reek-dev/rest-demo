package com.example.restdemo.service;

import com.example.restdemo.dto.ScheduleByIdDTO;
import com.example.restdemo.dto.ScheduleDTO;
import com.example.restdemo.dto.ScheduleListDTO;

import java.util.List;

public interface ScheduleService {

    ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);

    List<ScheduleListDTO> fetchSessionsInOrg(Long organisationId);

    ScheduleByIdDTO fetchScheduleByIdDtoById(Long scheduleId);
}
