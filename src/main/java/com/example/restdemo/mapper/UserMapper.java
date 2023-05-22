package com.example.restdemo.mapper;

import com.example.restdemo.dto.UserByOrgDTO;
import com.example.restdemo.dto.UserDetailsDTO;
import com.example.restdemo.dto.UserIdAndNameDTO;
import com.example.restdemo.entity.User;

public class UserMapper {

    public static UserDetailsDTO mapToUserDetailsDTO(User user) {

        return new UserDetailsDTO(
                user.getRole().ordinal(),
                user.getState().getId(),
                user.getCity().getId(),
                user.getAddress(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getGender().toString(),
                user.getDob(),
                user.getJoiningDate()
        );
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
