package com.example.restdemo.mapper;

import com.example.restdemo.dto.CityDTO;
import com.example.restdemo.entity.City;

public class CityMapper {

    public static CityDTO mapToCityDTO(City city) {
        return CityDTO.builder()
                .cityId(city.getId())
                .cityName(city.getName())
                .stateId(city.getState().getId())
                .build();
    }
}
