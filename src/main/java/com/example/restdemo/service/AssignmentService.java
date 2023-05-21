package com.example.restdemo.service;

import com.example.restdemo.dto.AssignmentByOrganisationDTO;
import com.example.restdemo.dto.CreateAssignmentDTO;
import com.example.restdemo.dto.GetAssignmentDTO;

import java.util.List;

public interface AssignmentService {

    public CreateAssignmentDTO createAssignment(CreateAssignmentDTO assignmentDTO);

    public GetAssignmentDTO fetchAssignmentById(Long id);

    public List<AssignmentByOrganisationDTO> fetchAssignmentByOrg(Long organisationId);

    public List<AssignmentByOrganisationDTO> fetchAssignmentByIns(Long instructorId);

    public List<AssignmentByOrganisationDTO> fetchAssignmentByOrgAndCourse(Long organisationId, Long courseId);

}
