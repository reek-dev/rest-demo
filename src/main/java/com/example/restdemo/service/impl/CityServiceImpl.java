package com.example.restdemo.service.impl;

import com.example.restdemo.entity.City;
import com.example.restdemo.exception.CityAlreadyExistsException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.repository.CityRepository;
import com.example.restdemo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public City createCity(City city) {
        if (cityRepository.existsByNameIgnoreCase(city.getName()))
            throw new CityAlreadyExistsException(city.getName());
        return cityRepository.save(city);
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City", "id", String.valueOf(id)));
    }
}
