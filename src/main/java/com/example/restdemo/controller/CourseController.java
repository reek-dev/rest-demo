package com.example.restdemo.controller;

import com.example.restdemo.dto.*;
import com.example.restdemo.entity.CourseCategory;
import com.example.restdemo.entity.Organisation;
import com.example.restdemo.service.CourseCategoryService;
import com.example.restdemo.service.CourseService;
import com.example.restdemo.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/course")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseCategoryService courseCategoryService;
    private final OrganisationService organizationService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<String>> createCourse(
            @RequestBody CreateCourseDTO courseDTO
            ) {
        CreateCourseDTO course = courseService.createCourse(courseDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Course created successfully.");

        ResponseDTO<String> response =
                ResponseDTO.<String>builder()
                        .status("Success")
                        .statusCode(HttpStatus.CREATED.value())
                        .message("Course created successfully")
                        .data(String.format("Course `%s` is successfully created.", course.getCourseName().trim()))
                        .build();


        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body(response);
    }

    @GetMapping("/getCourseDetails/{courseId}")
    public ResponseEntity<ResponseDTO<CourseDTO>> getCourseDetailsById(
            @PathVariable("courseId") Long courseId
    ) {
        CourseDTO courseDTO = courseService.getCourseDtoById(courseId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Course details fetched successfully.");

        ResponseDTO<CourseDTO> response =
                ResponseDTO.<CourseDTO>builder()
                        .status("Success")
                        .statusCode(HttpStatus.OK.value())
                        .message("Course details fetched successfully")
                        .data(courseDTO)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }

    @GetMapping("/getCourseList/all/{organisationId}")
    public ResponseEntity<ResponseDTO<List<CourseListDTO>>> getCoursesByOrganisation(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<CourseListDTO> courseListDTO = courseService.fetchCoursesByOrgnisation(organisationId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Course details fetched successfully.");

        ResponseDTO<List<CourseListDTO>> response =
                ResponseDTO.<List<CourseListDTO>>builder()
                        .status("Success")
                        .statusCode(HttpStatus.OK.value())
                        .message("Course details fetched successfully")
                        .data(courseListDTO)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);

    }

    @PostMapping("/create-category")
    public ResponseEntity<String> createCourseCategory(
            @RequestBody CourseCategory courseCategory,
            @RequestParam(value = "orgId", required = true) Long orgId
            ) {

        Set<Organisation> organisations = new HashSet<>();
        Organisation organisation = organizationService.getOrganizationById(orgId);

        organisations.add(organisation);

        courseCategory.setOrganisations(organisations);

        CourseCategory newCourseCategory = courseCategoryService.createCourseCategory(courseCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Category `" + newCourseCategory.getCategoryName() + "` was created.");
    }

    @GetMapping("/getCategoryAndCourseCount/all/{organisationId}")
    public ResponseEntity<ResponseDTO<List<CourseCountResponseDTO>>> fetchCourseCounts(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<CourseCountResponseDTO> courseListDTO = courseService.getCourseCountByCategory(organisationId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Course details fetched successfully.");

        ResponseDTO<List<CourseCountResponseDTO>> response =

                ResponseDTO.<List<CourseCountResponseDTO>>builder()
                        .status("Success")
                        .statusCode(HttpStatus.OK.value())
                        .message("Course details fetched successfully")
                        .data(courseListDTO)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }

    @GetMapping("/getAllCourseIdAndName/{instructorId}")
    public ResponseEntity<ResponseDTO<List<CourseIdAndNameDTO>>> fetchCourseByInstructor(
            @PathVariable("instructorId") Long instructorId
    ) {
        List<CourseIdAndNameDTO> courseByInstructor = courseService.getCourseByInstructor(instructorId);

        ResponseDTO<List<CourseIdAndNameDTO>> response =
                ResponseDTO.<List<CourseIdAndNameDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("courses fetched successfully.")
                        .data(courseByInstructor)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("update/{courseId}")
    public ResponseEntity<ResponseDTO<String>> updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestBody CourseDTO courseDTO
    ) {

        courseDTO.setCourseId(courseId);

        courseService.updateCourse(courseDTO);

        ResponseDTO<String> response =
                ResponseDTO.<String>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("course updated successfully.")
                        .data(null)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

    }

}
