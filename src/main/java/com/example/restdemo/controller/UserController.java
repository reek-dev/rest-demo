package com.example.restdemo.controller;


import com.example.restdemo.dto.*;
import com.example.restdemo.entity.Role;
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

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<String>> createUser(
            @RequestBody CreateUserDTO userDTO
    ) {

        CreateUserDTO user = userService.createUser(userDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "User created successfully.");

        ResponseDTO<String> response =

                ResponseDTO.<String>builder()
                        .status("Success")
                        .statusCode(HttpStatus.CREATED.value())
                        .message("User created successfully")
                        .data(String.format("User `%s %s` is successfully created.", user.getFirstName().trim(), user.getLastName().trim()))
                        .build();


        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }

    @GetMapping("/getUserDetails/{userId}")
    public ResponseEntity<ResponseDTO<UserDetailsDTO>> getUserDetails(
            @PathVariable("userId") Long userId
    ) {
        UserDetailsDTO userDetailsDtoById = userService.getUserDetailsDtoById(userId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "User details fetched successfully.");

        ResponseDTO<UserDetailsDTO> response =
                ResponseDTO.<UserDetailsDTO>builder()
                        .status("Success")
                        .statusCode(HttpStatus.OK.value())
                        .message("User details fetched successfully.")
                        .data(userDetailsDtoById)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }

    @GetMapping("/getUserList/all/{organisationId}")
    public ResponseEntity<ResponseDTO<List<UserByOrgDTO>>> getUserDetailsByOrg(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<UserByOrgDTO> userByOrgDTO = userService.getUserListByOrgDtoByOrgId(organisationId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Message", "User details fetched successfully.");

        ResponseDTO<List<UserByOrgDTO>> response =
                ResponseDTO.<List<UserByOrgDTO>>builder()
                        .status("Success")
                        .statusCode(HttpStatus.OK.value())
                        .message("User details fetched successfully.")
                        .data(userByOrgDTO)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }

    @GetMapping("getRoleAndUserCount/all/{organisationId}")
    public ResponseEntity<ResponseDTO<List<UserCountResponseDTO>>> getUserRolesCountByOrgId(
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

        ResponseDTO<List<UserCountResponseDTO>> response =
                ResponseDTO.<List<UserCountResponseDTO>>builder()
                        .status("Success")
                        .statusCode(HttpStatus.OK.value())
                        .message("User role details fetched successfully.")
                        .data(list)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .body(response);
    }

    @GetMapping("/getAllUserIdAndFirstNameAndLastName/{organisationId}/{roleId}")
    public ResponseEntity<ResponseDTO<List<UserIdAndNameDTO>>> getUserByOrganisationAndRole(
            @PathVariable("organisationId") Long organisationId,
            @PathVariable("roleId") int roleId
    ) {

        List<UserIdAndNameDTO> userIdAndNameByOrgAndRole = userService.getUserIdAndNameByOrgAndRole(organisationId, roleId);

        ResponseDTO<List<UserIdAndNameDTO>> response =
                ResponseDTO.<List<UserIdAndNameDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("users fetched successfully")
                        .data(userIdAndNameByOrgAndRole)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
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
