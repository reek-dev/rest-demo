package com.example.restdemo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserIdAndNameDTO {
    private Long userId;
    private String firstName;
    private String lastName;
}
