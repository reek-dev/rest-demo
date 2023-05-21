package com.example.restdemo.service;

import com.example.restdemo.dto.CityDTO;
import com.example.restdemo.entity.City;

import java.util.List;

public interface CityService {

    public City createCity(City city);
    public City getCityById(Long id);

    public List<CityDTO> fetchCities();
}
