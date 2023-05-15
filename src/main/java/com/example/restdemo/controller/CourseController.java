package com.example.restdemo.controller;

import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.CourseCategory;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.NotATeacherException;
import com.example.restdemo.service.CourseCategoryService;
import com.example.restdemo.service.CourseService;
import com.example.restdemo.service.OrganizationService;
import com.example.restdemo.service.UserService;
import lombok.RequiredArgsConstructor;
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
    private final OrganizationService organizationService;

    /* RESTful APIs for creation */

    // REST API: create a course
    @PostMapping("/create")
    public ResponseEntity<String> createCourse(
            @RequestBody Course course,
            @RequestParam(name = "organisationId", required = false) Long orgId,
            @RequestParam(name = "courseCategoryId", required = false) Long categoryId
//            @RequestParam(name = "instructorIds", required = false) List<Long> instructorIds

    ) {

        System.out.println("course object that came in the request body: " + course);
        System.out.println(orgId);
        System.out.println(categoryId);


        if (orgId != null)
            course.setOrganisation(organizationService.getOrganizationById(orgId));

        System.out.println("after setting the organisation: " + course);

        if (categoryId != null)
            course.setCategory(courseCategoryService.getCourseCategoryById(categoryId));

        System.out.println("after setting the category: " + course);

        Course newCourse = courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Course `" + newCourse.getCourseName() + "` is successfully created.");
    }

    @PostMapping("/create-category")
    public ResponseEntity<String> createCourseCategory(@RequestBody CourseCategory courseCategory) {
        CourseCategory newCourseCategory = courseCategoryService.createCourseCategory(courseCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Category `" + newCourseCategory.getCategoryName() + "` was created.");
    }

    @PutMapping("/{courseId}/assign-ins/{instructorId}")
    public ResponseEntity<String> assignInstructorsToCourse(
            @PathVariable("courseId") Long courseId,
            @PathVariable("instructorId") Long instructorId
    ) {

        User instructor = userService.getUserById(instructorId);
        Course course = courseService.getCourseById(courseId);

        instructor.getAssignedCourses().add(course);

        userService.saveUser(instructor);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Instructor was successfully assigned.");
    }

//    @GetMapping("/getall/idandname")
//    public ResponseEntity<List<CourseIdAndNameDTO>> getAllCourseIdAndName() {
//        List<CourseIdAndNameDTO> users = courseService.getAllCourseIdAndName();
//        return ResponseEntity.status(HttpStatus.OK).body(users);
//    }
//
//    @GetMapping("/getall/list")
//    public ResponseEntity<List<CoursesListDTO>> getAllCoursesList() {
//        List<CoursesListDTO> users = courseService.getAllCoursesList();
//        return ResponseEntity.status(HttpStatus.OK).body(users);
//    }
//
//    @GetMapping("/getall/category")
//    public ResponseEntity<List<CourseCategoryDTO>> getAllCourseCategories() {
//
//    }
//
//    // REST API: get a single course by id
//    @GetMapping("/get/{id}")
//    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Long id) {
//        CourseDTO course = courseService.getCourseById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(course);
//    }
}
