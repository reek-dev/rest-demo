package com.example.restdemo.controller;


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


//    /* RESTful APIs for retrieval */
//
//    // REST API: get all users []
//    @GetMapping("/getall/idandname")
//    public ResponseEntity<List<UserIdAndNameDTO>> getAllUserIdAndName() {
//        List<UserIdAndNameDTO> users = userService.getAllUserIdAndName();
//        return ResponseEntity.status(HttpStatus.OK).body(users);
//    }
//
//
//    // REST API: get all users []
//    @GetMapping("/getall/list")
//    public ResponseEntity<List<UsersListDTO>> getAllUsersList() {
//        List<UsersListDTO> users = userService.getAllUsersList();
//        return ResponseEntity.status(HttpStatus.OK).body(users);
//    }
//
//    // REST API: get all users
//    @GetMapping("/getall")
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        List<UserDTO> users = userService.getAllUsers();
//        return ResponseEntity.status(HttpStatus.OK).body(users);
//    }
//
//    // REST API: get a single user by id
//    @GetMapping("/get/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
//        UserDTO user = userService.getUserById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(user);
//    }
//
//    // REST API: update a user
//    @PutMapping("/update/{id}")
//    public ResponseEntity<UserDTO> updateUser(
//            @PathVariable("id") Long id,
//            @RequestBody UserDTO user
//            ) {
//        user.setId(id);
//        UserDTO updatedUser = userService.updateUser(user);
//        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
//    }
//
//    // REST API: delete a user
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(String.format("User with id: `%s` is successfully deleted.", String.valueOf(id)));
//    }
}
