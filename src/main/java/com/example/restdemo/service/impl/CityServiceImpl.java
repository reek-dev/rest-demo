package com.example.restdemo.service.impl;

import com.example.restdemo.dto.CityDTO;
import com.example.restdemo.entity.City;
import com.example.restdemo.exception.CityAlreadyExistsException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.mapper.CityMapper;
import com.example.restdemo.repository.CityRepository;
import com.example.restdemo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<CityDTO> fetchCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(CityMapper::mapToCityDTO)
                .collect(Collectors.toList());
    }
}
