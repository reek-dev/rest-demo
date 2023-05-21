package com.example.restdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateDTO {
    private Long stateId;
    private String stateName;
}
