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
public class UserByOrgDTO {

    private Long UserId;
    private String Name;
    private String Email;
    private String RoleName;
    private LocalDate JoiningDate;
}
