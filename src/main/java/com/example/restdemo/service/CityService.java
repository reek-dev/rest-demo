package com.example.restdemo.service;

import com.example.restdemo.entity.City;

public interface CityService {

    public City createCity(City city);
    public City getCityById(Long id);
}
