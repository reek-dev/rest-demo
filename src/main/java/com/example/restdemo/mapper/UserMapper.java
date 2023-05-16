package com.example.restdemo.mapper;

import com.example.restdemo.dto.UserDetailsDTO;
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
}
