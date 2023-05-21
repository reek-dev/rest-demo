package com.example.restdemo.controller;

import com.example.restdemo.dto.*;
import com.example.restdemo.service.AssignmentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<String>> createAssignment(
            @RequestBody CreateAssignmentDTO assignmentDTO) {

        assignmentService.createAssignment(assignmentDTO);

        ResponseDTO<String> response =
                ResponseDTO.<String>builder()
                        .status("success")
                        .statusCode(HttpStatus.CREATED.value())
                        .message("assignment created.")
                        .data("assignment created.")
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }


    @GetMapping("/getAllAssignments/org/{organisationId}")
    public ResponseEntity<ResponseDTO<List<AssignmentByOrganisationDTO>>> fetchAssignmentsByOrg(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<AssignmentByOrganisationDTO> assignmentByOrganisationDTOs = assignmentService.fetchAssignmentByOrg(organisationId);

        ResponseDTO<List<AssignmentByOrganisationDTO>> response =
                ResponseDTO.<List<AssignmentByOrganisationDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("assignments fetched successfully.")
                        .data(assignmentByOrganisationDTOs)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }

    @GetMapping("/getAllAssignments/ins/{instructorId}")
    public ResponseEntity<ResponseDTO<List<AssignmentByOrganisationDTO>>> fetchAssignmentsByIns(
            @PathVariable("instructorId") Long instructorId
    ) {
        List<AssignmentByOrganisationDTO> assignmentByOrganisationDTOs = assignmentService.fetchAssignmentByIns(instructorId);

        ResponseDTO<List<AssignmentByOrganisationDTO>> response =
                ResponseDTO.<List<AssignmentByOrganisationDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("assignments fetched successfully.")
                        .data(assignmentByOrganisationDTOs)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/getAllAssignments")
    public ResponseEntity<ResponseDTO<List<AssignmentByOrganisationDTO>>> fetchAllAssignments(
            @RequestBody AllAssignmentRequest request
            ) {

        List<AssignmentByOrganisationDTO> assignmentByOrganisationDTOs =
                assignmentService.fetchAssignmentByOrgAndCourse(request.organisationId, request.courseId);

        ResponseDTO<List<AssignmentByOrganisationDTO>> response =
                ResponseDTO.<List<AssignmentByOrganisationDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("assignments fetched successfully.")
                        .data(assignmentByOrganisationDTOs)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/getAssignment/{assignmentId}")
    public ResponseEntity<ResponseDTO<GetAssignmentDTO>> fetchAssignmentById(
            @PathVariable("assignmentId") Long assignmentId
    ) {
        GetAssignmentDTO getAssignmentDTO = assignmentService.fetchAssignmentById(assignmentId);

        ResponseDTO<GetAssignmentDTO> response =
                ResponseDTO.<GetAssignmentDTO>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("assignment fetched successfully.")
                        .data(getAssignmentDTO)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

}
