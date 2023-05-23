package com.example.restdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {
    private String Role;
    private Long StateId;
    private Long CityId;
    private String Address;
    private String EmailAddress;
    private String password;
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String Gender;
    private LocalDate DateOfBirth;
    private LocalDate JoiningDate;
}
