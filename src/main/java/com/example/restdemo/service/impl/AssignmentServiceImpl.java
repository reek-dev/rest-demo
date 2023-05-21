package com.example.restdemo.service.impl;

import com.example.restdemo.dto.AssignmentByOrganisationDTO;
import com.example.restdemo.dto.CreateAssignmentDTO;
import com.example.restdemo.dto.GetAssignmentDTO;
import com.example.restdemo.entity.Assignment;
import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.Organisation;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.NotATeacherException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.mapper.AssignmentMapper;
import com.example.restdemo.repository.AssignmentRepository;
import com.example.restdemo.repository.CourseRepository;
import com.example.restdemo.repository.OrganisationRepository;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final OrganisationRepository organisationRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;


    @Override
    public CreateAssignmentDTO createAssignment(CreateAssignmentDTO assignmentDTO) {

        Organisation organisation = organisationRepository.findById(assignmentDTO.getOrganisationId())
                .orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", String.valueOf(assignmentDTO.getOrganisationId())));

        Course course = courseRepository.findById(assignmentDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(assignmentDTO.getCourseId())));

        User user = userRepository.findById(assignmentDTO.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(assignmentDTO.getInstructorId())));

        if (!user.getRole().toString().equals("TEACHER"))
            throw new NotATeacherException(assignmentDTO.getInstructorId());


        Assignment assignment = Assignment.builder()
                .organisation(organisation)
                .title(assignmentDTO.getTitle())
                .course(course)
                .instructor(user)
                .maxPoint(assignmentDTO.getMaxPoint())
                .fileTypeAllowed(assignmentDTO.getFileTypeAllowed())
                .maxFileSize(assignmentDTO.getMaxFileSize())
                .dueDate(LocalDate.parse(assignmentDTO.getDueDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .fileName(assignmentDTO.getFileName())
                .assignmentType(assignmentDTO.getAssignmentType())
                .build();

        assignmentRepository.save(assignment);
        return null;
    }

    @Override
    public GetAssignmentDTO fetchAssignmentById(Long id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment", "id", String.valueOf(id)));
        return AssignmentMapper.mapToGetAssignmentDTO(assignment);
    }

    @Override
    public List<AssignmentByOrganisationDTO> fetchAssignmentByOrg(Long organisationId) {
        List<Assignment> assignments = assignmentRepository.fetchAssignmentByOrganisation(organisationId);
        return assignments.stream()
                .map(AssignmentMapper::mapToAssignmentByOrganisationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssignmentByOrganisationDTO> fetchAssignmentByIns(Long instructorId) {

        List<Assignment> assignments = assignmentRepository.fetchAssignmentByInstructor(instructorId);
        return assignments.stream()
                .map(AssignmentMapper::mapToAssignmentByOrganisationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssignmentByOrganisationDTO> fetchAssignmentByOrgAndCourse(Long organisationId, Long courseId) {

        List<Assignment> assignments = assignmentRepository.fetchAssignmentByOrgAndCourse(organisationId, courseId);

        return assignments.stream()
                .map(AssignmentMapper::mapToAssignmentByOrganisationDTO)
                .collect(Collectors.toList());
    }
}
