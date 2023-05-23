package com.example.restdemo.mapper;

import com.example.restdemo.dto.UserByOrgDTO;
import com.example.restdemo.dto.UserDTO;
import com.example.restdemo.dto.UserDetailsDTO;
import com.example.restdemo.dto.UserIdAndNameDTO;
import com.example.restdemo.entity.User;

import java.time.format.DateTimeFormatter;

public class UserMapper {

    public static UserDetailsDTO mapToUserDetailsDTO(User user) {

        return new UserDetailsDTO(
                user.getRole().toString(),
                user.getState().getId(),
                user.getCity().getId(),
                user.getAddress(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getGender().toString(),
                user.getDob(),
                user.getJoiningDate()
        );
    }

    public static UserDTO mapToUserDTO(User user) {

        return UserDTO.builder()
                .userId(user.getId())
                .role(user.getRole().toString())
                .stateId(user.getState().getId())
                .cityId(user.getCity().getId())
                .address(user.getAddress())
                .emailAddress(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhone())
                .gender(user.getGender().toString())
                .dateOfBirth(user.getDob().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .joiningDate(user.getJoiningDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .build();
    }

    public static UserByOrgDTO mapToUserByOrgDTO(User user) {

        return new UserByOrgDTO(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getRole().toString(),
                user.getJoiningDate()
        );
    }

    public static UserIdAndNameDTO mapToUserIdAndNameDTO(User user) {

        return UserIdAndNameDTO.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
