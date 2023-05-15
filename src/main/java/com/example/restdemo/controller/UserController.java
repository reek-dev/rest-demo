package com.example.restdemo.controller;


import com.example.restdemo.dto.UserDTO;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.service.CityService;
import com.example.restdemo.service.CourseService;
import com.example.restdemo.service.StateService;
import com.example.restdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final CityService cityService;

    private final StateService stateService;

    private final CourseService courseService;

    /* RESTful APIs for creation */

    // REST API: create a user
    @PostMapping("/create")
    public ResponseEntity<String> createUser(
            @RequestBody User user,
            @RequestParam(name = "stateId", required = false) Long stateId,
            @RequestParam(name = "cityId", required = false) Long cityId,
            @RequestParam(name = "courseIds", required = false) List<Long> courseIds

    ) {

        if (cityId != null)
            user.setCity(cityService.getCityById(cityId));

        if (stateId != null)
            user.setState(stateService.getStateById(stateId));

        if (courseIds != null) {
            for (Long id : courseIds) {
                if (courseService.getCourseById(id) == null) {
                    throw new ResourceNotFoundException("Course", "id", String.valueOf(id));
                } else {
                    user.getAssignedCourses().add(courseService.getCourseById(id));
                }
            }
        }

        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User is successfully created.");
    }


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("userId") Long userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("User with id: `%d` is successfully deleted.", userId));
    }


}
