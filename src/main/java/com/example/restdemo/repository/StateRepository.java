package com.example.restdemo.repository;

import com.example.restdemo.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {

    Boolean existsByNameIgnoreCase(String name);
}
