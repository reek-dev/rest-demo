package com.example.restdemo.repository;

import com.example.restdemo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    Boolean existsByNameIgnoreCase(String name);
}
