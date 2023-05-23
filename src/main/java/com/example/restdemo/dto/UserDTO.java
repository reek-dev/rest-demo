package com.example.restdemo.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private Long userId;
    private String role;
    private Long stateId;
    private Long cityId;
    private String address;
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String dateOfBirth;
    private String joiningDate;
}
