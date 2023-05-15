package com.example.restdemo.dto;

import com.example.restdemo.entity.*;

import java.time.LocalDate;

public record UserDTO(
        Long id,
        Role role,
        Organisation organisation,
        State state,
        City city,
        String email,
        String firstName,
        String lastName,
        String phone,
        String address,
        Gender gender,
        LocalDate dob,
        LocalDate joiningDate
) {
}
