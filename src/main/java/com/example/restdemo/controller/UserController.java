package com.example.restdemo.controller;


import com.example.restdemo.dto.*;
import com.example.restdemo.entity.Role;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    private final CityService cityService;

    private final StateService stateService;

    private final OrganisationService organisationService;

    private final CourseService courseService;

    /* RESTful APIs for creation */

    // REST API: create a user
//    @PostMapping("/create")
//    public ResponseEntity<String> createUser(
//            @RequestBody User user,
//            @RequestParam(name = "stateId", required = false) Long stateId,
//            @RequestParam(name = "cityId", required = false) Long cityId,
//            @RequestParam(name = "orgId", required = true) Long orgId,
//            @RequestParam(name = "courseIds", required = false) List<Long> courseIds
//
//    ) {
//
//        if (cityId != null)
//            user.setCity(cityService.getCityById(cityId));
//
//        if (stateId != null)
//            user.setState(stateService.getStateById(stateId));
//
//        if (orgId != null)
//            user.setOrganisation(organisationService.getOrganizationById(orgId));
//
//        if (courseIds != null) {
//            for (Long id : courseIds) {
//                if (courseService.getCourseById(id) == null) {
//                    throw new ResourceNotFoundException("Course", "id", String.valueOf(id));
//                } else {
//                    user.getAssignedCourses().add(courseService.getCourseById(id));
//                }
//            }
//        }
//
//        userService.createUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body("User is successfully created.");
//    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(
           @RequestBody CreateUserDTO userDTO
    ) {

        CreateUserDTO user = userService.createUser(userDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "User created successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body("User `" + user.getFirstName().trim() + " " + user.getLastName().trim() + "` successfully created.");
    }

    @GetMapping("/getUserDetails/{userId}")
    public ResponseEntity<UserDetailsDTO> getUserDetails(
            @PathVariable("userId") Long userId
    ) {
        UserDetailsDTO userDetailsDtoById = userService.getUserDetailsDtoById(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "User details fetched successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(userDetailsDtoById);
    }

    @GetMapping("/getUserList/all/{organisationId}")
    public ResponseEntity<List<UserByOrgDTO>> getUserDetailsByOrg(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<UserByOrgDTO> userByOrgDTO = userService.getUserListByOrgDtoByOrgId(organisationId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "User details fetched successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(userByOrgDTO);
    }

    @GetMapping("getRoleAndUserCount/all/{organisationId}")
    public ResponseEntity<List<UserCountResponseDTO>> getUserRolesCountByOrgId(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<Object[]> roleCounts = userRepository.countUsersByRole(organisationId);

        List<UserCountResponseDTO> list = new ArrayList<>();

        for (Object[] roleCount : roleCounts) {

            Role r = (Role) roleCount[0];
            String role = r.toString();
            Long count = (Long) roleCount[1];

            UserCountResponseDTO dto = new UserCountResponseDTO(role, count);
            list.add(dto);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "User role details fetched successfully.");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(list);
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
