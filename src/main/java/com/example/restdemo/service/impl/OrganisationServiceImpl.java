package com.example.restdemo.service.impl;

import com.example.restdemo.entity.Organisation;
import com.example.restdemo.exception.ResourceAlreadyExistsException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.repository.OrganisationRepository;
import com.example.restdemo.service.OrganisationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganisationServiceImpl implements OrganisationService {

    private final OrganisationRepository organisationRepository;

    @Override
    public Organisation getOrganizationById(Long id) {
        return organisationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization", "id", String.valueOf(id)));
    }

    @Override
    public Organisation createOrganisation(Organisation organisation) {
        if (organisationRepository.existsByNameIgnoreCase(organisation.getName()))
            throw new ResourceAlreadyExistsException("Organisation", "name", organisation.getName());
        return organisationRepository.save(organisation);
    }
}
