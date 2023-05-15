package com.example.restdemo.service;

import com.example.restdemo.entity.Organisation;

public interface OrganizationService {

    public Organisation getOrganizationById(Long id);

    public Organisation createOrganisation(Organisation organisation);

}
