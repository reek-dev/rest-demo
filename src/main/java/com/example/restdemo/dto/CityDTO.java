package com.example.restdemo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDTO {
    private Long cityId;
    private String cityName;
    private Long stateId;
}
