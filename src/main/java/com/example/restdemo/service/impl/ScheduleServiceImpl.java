package com.example.restdemo.service.impl;

import com.example.restdemo.dto.ScheduleByIdDTO;
import com.example.restdemo.dto.ScheduleDTO;
import com.example.restdemo.dto.ScheduleListDTO;
import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.Organisation;
import com.example.restdemo.entity.Schedule;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.NotATeacherException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.mapper.ScheduleMapper;
import com.example.restdemo.repository.CourseRepository;
import com.example.restdemo.repository.OrganisationRepository;
import com.example.restdemo.repository.ScheduleRepository;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final OrganisationRepository organisationRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {

        Organisation organisation = organisationRepository.findById(scheduleDTO.getOrganisationId())
                .orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", String.valueOf(scheduleDTO.getOrganisationId())));


        Course course = courseRepository.findById(scheduleDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(scheduleDTO.getCourseId())));

        User user = userRepository.findById(scheduleDTO.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor", "id", String.valueOf(scheduleDTO.getInstructorId())));

        if (!user.getRole().toString().equals("TEACHER"))
            throw new NotATeacherException(scheduleDTO.getInstructorId());

        LocalDate scheduleDate = LocalDate.parse(scheduleDTO.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalTime scheduleTime = LocalTime.parse(scheduleDTO.getTime(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime scheduleDuration = LocalTime.parse(scheduleDTO.getDuration(), DateTimeFormatter.ofPattern("HH:mm"));

        Schedule schedule = new Schedule();
        schedule.setOrganisation(organisation);
        schedule.setDate(scheduleDate);
        schedule.setTime(scheduleTime);
        schedule.setDuration(scheduleDuration);
        schedule.setCourse(course);
        schedule.setInstructor(user);
        scheduleRepository.save(schedule);

        return scheduleDTO;
    }

    @Override
    public List<ScheduleListDTO> fetchSessionsInOrg(Long organisationId) {

        List<Schedule> schedules = scheduleRepository.fetchSchedulesByOrg(organisationId);
        return schedules.stream()
                .map(ScheduleMapper::mapToScheduleListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleByIdDTO fetchScheduleByIdDtoById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Schedule", "id", String.valueOf(scheduleId)));
        return ScheduleMapper.mapToScheduleByIdDTO(schedule);
    }
}
