package com.example.restdemo.repository;

import com.example.restdemo.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    Boolean existsByNameIgnoreCase(String name);
}
