package com.example.restdemo.controller;

import com.example.restdemo.dto.CourseCountResponseDTO;
import com.example.restdemo.dto.CourseDTO;
import com.example.restdemo.dto.CourseListDTO;
import com.example.restdemo.dto.CreateCourseDTO;
import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.CourseCategory;
import com.example.restdemo.entity.Organisation;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.NotATeacherException;
import com.example.restdemo.service.CourseCategoryService;
import com.example.restdemo.service.CourseService;
import com.example.restdemo.service.OrganisationService;
import com.example.restdemo.service.UserService;
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
    private final UserService userService;
    private final OrganisationService organizationService;

    /* RESTful APIs for creation */

    // REST API: create a course
//    @PostMapping("/create")
//    public ResponseEntity<String> createCourse(
//            @RequestBody Course course,
//            @RequestParam(name = "organisationId", required = false) Long orgId,
//            @RequestParam(name = "courseCategoryId", required = false) Long categoryId,
//            @RequestParam(name = "instructorIds", required = false) List<Long> instructorIds
//
//    ) {
//
//        if (orgId != null)
//            course.setOrganisation(organizationService.getOrganizationById(orgId));
//
//        if (categoryId != null)
//            course.setCategory(courseCategoryService.getCourseCategoryById(categoryId));
//
//        Set<User> possibleInstructors = new HashSet<>();
//
//        if (instructorIds != null) {
//            for (Long id : instructorIds) {
//                User user = userService.getUserById(id);
//                if (!user.getRole().toString().equals("TEACHER"))
//                    throw new NotATeacherException(id);
//                else possibleInstructors.add(user);
//            }
//        }
//
//        if (!possibleInstructors.isEmpty())
//            course.setAssociatedUsers(possibleInstructors);
//
//
//        Course newCourse = courseService.createCourse(course);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body("Course `" + newCourse.getCourseName() + "` is successfully created.");
//    }

    @PostMapping("/create")
    public ResponseEntity<String> createCourse(
            @RequestBody CreateCourseDTO courseDTO
            ) {
        CreateCourseDTO course = courseService.createCourse(courseDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Course created successfully.");

        return ResponseEntity.status(HttpStatus.CREATED)
                .headers(headers)
                .body("Course `" + course.getCourseName() + "` is successfully created.");
    }

    @GetMapping("/getCourseDetails/{courseId}")
    public ResponseEntity<CourseDTO> getCourseDetailsById(
            @PathVariable("courseId") Long courseId
    ) {
        CourseDTO courseDTO = courseService.getCourseDtoById(courseId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Course details fetched successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(courseDTO);
    }

    @GetMapping("/getCourseList/all/{organisationId}")
    public ResponseEntity<List<CourseListDTO>> getCoursesByOrganisation(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<CourseListDTO> courseListDTO = courseService.fetchCoursesByOrgnisation(organisationId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Course details fetched successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(courseListDTO);

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
    public ResponseEntity<List<CourseCountResponseDTO>> fetchCourseCounts(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<CourseCountResponseDTO> courseListDTO = courseService.getCourseCountByCategory(organisationId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "Course details fetched successfully.");


        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(courseListDTO);
    }


    @PutMapping("/update-ins/{courseId}")
    public ResponseEntity<String> detachInstructorFromCourse(
            @PathVariable("courseId") Long courseId,
            @RequestParam(name = "instructorIds", required = true) List<Long> instructorIds
    ) {

        Course possibleCourse = courseService.getCourseById(courseId);

        Set<User> possibleInstructors = new HashSet<>();

        possibleCourse.setAssociatedUsers(possibleInstructors);

        for (Long id : instructorIds) {
            User possibleInstructor = userService.getUserById(id);
            if (!possibleInstructor.getRole().toString().equals("TEACHER"))
                throw new NotATeacherException(id);
            else possibleInstructors.add(possibleInstructor);

        }

        if (!possibleInstructors.isEmpty())
            possibleCourse.setAssociatedUsers(possibleInstructors);

        courseService.saveCourse(possibleCourse);

        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("Successfully updated the course with id: `%d`", courseId));
    }

}
